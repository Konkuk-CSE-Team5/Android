package com.konkuk.hackathon.feature.volunteer

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.core.navigation.component.VolunteerBottomBar
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
                val context = LocalContext.current
                var backPressedTime by remember { mutableLongStateOf(0L) }
                val volunteerNavigator = rememberVolunteerNavigator()

                BackHandler {
                    val currentTime = System.currentTimeMillis()
                    if (currentTime - backPressedTime < 2000) {
                        finish()
                    } else {
                        backPressedTime = currentTime
                        Toast.makeText(context, "뒤로 가기 버튼을 한 번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

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