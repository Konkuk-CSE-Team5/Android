package com.konkuk.hackathon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.core.feature.user.setting.screen.UserSettingScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnItTheme {
                UserSettingScreen()
//                val mainNavigator = rememberMainNavigator()
//
//                Scaffold(
//                    modifier = Modifier.fillMaxSize(),
//                    bottomBar = {
//                        MainBottomBar(
//                            visible = mainNavigator.shouldShowBottomBar(),
//                            tabs = MainTab.entries,
//                            currentTab = mainNavigator.currentTab,
//                            onTabSelected = {
//                                mainNavigator.navigate(it)
//                            },
//                        )
//                    }
//                ) { innerPadding ->
//                    MainNavHost(
//                        padding = innerPadding,
//                        navController = mainNavigator.navController,
//                    )
//                }
            }
        }
    }
}