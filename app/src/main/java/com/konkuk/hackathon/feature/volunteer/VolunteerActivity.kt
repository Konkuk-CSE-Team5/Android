package com.konkuk.hackathon.feature.volunteer

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
import com.konkuk.hackathon.core.navigation.component.VolunteerBottomBar
import com.konkuk.hackathon.core.navigation.rememberMainNavigator
import com.konkuk.hackathon.core.navigation.volunteer.VolunteerNavHost
import com.konkuk.hackathon.core.navigation.volunteer.VolunteerTab
import com.konkuk.hackathon.core.navigation.volunteer.rememberVolunteerNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VolunteerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnItTheme {
                val volunteerNavigator = rememberVolunteerNavigator()

                Scaffold(
                    containerColor = Color.White,
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        VolunteerBottomBar(
                            visible = volunteerNavigator.shouldShowBottomBar(),
                            tabs = VolunteerTab.entries,
                            currentTab = volunteerNavigator.currentTab,
                            onTabSelected = {
                                volunteerNavigator.navigate(it)
                            },
                        )
                    }
                ) { innerPadding ->
                    VolunteerNavHost(
                        padding = innerPadding,
                        navigator = volunteerNavigator,
                    )
                }
            }
        }
    }
}