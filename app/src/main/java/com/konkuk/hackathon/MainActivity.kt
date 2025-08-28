package com.konkuk.hackathon

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.core.navigation.MainNavHost
import com.konkuk.hackathon.core.navigation.MainTab
import com.konkuk.hackathon.core.navigation.component.MainBottomBar
import com.konkuk.hackathon.core.navigation.rememberMainNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnItTheme {
                val context = LocalContext.current

                val permissionLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestPermission(),
                    onResult = { isGranted: Boolean ->
                        if (isGranted) {
                            // 권한이 허용되었을 때의 로직
                            println("알림 권한이 허용되었습니다.")
                        } else {
                            // 권한이 거부되었을 때의 로직
                            println("알림 권한이 거부되었습니다.")
                        }
                    }
                )
                LaunchedEffect(Unit) {
                    // Android 13 (API 33) 이상에서만 실행
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        // 이미 권한이 있는지 확인
                        val checkPermission = ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.POST_NOTIFICATIONS
                        )
                        // 권한이 없다면 요청
                        if (checkPermission != PackageManager.PERMISSION_GRANTED) {
                            permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                        }
                    }
                }

                val mainNavigator = rememberMainNavigator()

                Scaffold(
                    containerColor = Color.White,
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        MainBottomBar(
                            visible = mainNavigator.shouldShowBottomBar(),
                            tabs = MainTab.entries,
                            currentTab = mainNavigator.currentTab,
                            onTabSelected = {
                                mainNavigator.navigate(it)
                            },
                        )
                    }
                ) { innerPadding ->
                    MainNavHost(
                        padding = innerPadding,
                        navigator = mainNavigator,
                    )
                }
            }
        }
    }
}