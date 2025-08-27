package com.konkuk.hackathon.feature.center

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.core.navigation.center.CenterNavHost
import com.konkuk.hackathon.core.navigation.center.CenterTab
import com.konkuk.hackathon.core.navigation.center.rememberCenterNavigator
import com.konkuk.hackathon.core.navigation.component.CenterBottomBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CenterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnItTheme {
                val centerNavigator = rememberCenterNavigator()

                Scaffold(
                    containerColor = Color.White,
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        CenterBottomBar(
                            visible = centerNavigator.shouldShowBottomBar(),
                            tabs = CenterTab.entries,
                            currentTab = centerNavigator.currentTab,
                            onTabSelected = {
                                centerNavigator.navigate(it)
                            },
                        )
                    }
                ) { innerPadding ->
                    CenterNavHost(
                        padding = innerPadding,
                        navigator = centerNavigator,
                    )
                }
            }
        }
    }
}