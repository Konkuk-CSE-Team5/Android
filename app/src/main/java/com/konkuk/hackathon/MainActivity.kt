package com.konkuk.hackathon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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