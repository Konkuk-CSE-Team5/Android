package com.konkuk.hackathon

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.core.navigation.MainNavHost
import com.konkuk.hackathon.core.navigation.MainTab
import com.konkuk.hackathon.core.navigation.component.MainBottomBar
import com.konkuk.hackathon.core.navigation.rememberMainNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnItTheme {

                val mainNavigator = rememberMainNavigator()
                val multiplePermissionsState = rememberMultiplePermissionsState(
                    permissions =
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                            listOf(
                                Manifest.permission.CALL_PHONE,
                                Manifest.permission.READ_CALL_LOG,
                                Manifest.permission.POST_NOTIFICATIONS
                            )
                        else listOf(
                            Manifest.permission.CALL_PHONE,
                            Manifest.permission.READ_CALL_LOG,
                        )
                )
                LaunchedEffect(Unit) {
                    multiplePermissionsState.launchMultiplePermissionRequest()
                }

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