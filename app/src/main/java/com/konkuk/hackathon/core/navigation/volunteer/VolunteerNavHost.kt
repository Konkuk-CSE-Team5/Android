package com.konkuk.hackathon.core.navigation.volunteer

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.konkuk.hackathon.core.navigation.Route
import com.konkuk.hackathon.feature.volunteer.home.screen.RecordSubmitScreen
import com.konkuk.hackathon.feature.volunteer.home.screen.VolunteerHomeScreen
import java.time.LocalDateTime

@Composable
fun VolunteerNavHost(
    padding: PaddingValues,
    navigator: VolunteerNavigator,
) {
    val navController = navigator.navController

    NavHost(
        navController = navController,
        startDestination = navigator.startDestination,
    ) {
        // Home
        composable<VolunteerTabRoute.Home> {
            VolunteerHomeScreen(
                padding = padding,
                navigateToRecordSubmit = {
                    navController.navigate(
                        VolunteerRoute.HomeGraph(
                            id = 1,
                            elderName = "김재훈",
                            phone = "010-9460-1439",
                            startTime = LocalDateTime.now().toString()
                        ) // 수정 필요
                    )
                })
        }

        // Home Nested Graph
        navigation<VolunteerRoute.HomeGraph>(
            startDestination = VolunteerRoute.RecordSubmit
        ) {
            composable<VolunteerRoute.RecordSubmit> {
                val parentEntry = remember(it) {
                    navController.getBackStackEntry<VolunteerRoute.HomeGraph>()
                }
                val graphRoute = parentEntry.toRoute<VolunteerRoute.HomeGraph>()

                RecordSubmitScreen(
                    padding = padding,
                    popBackStack = { navController.popBackStack() },
                    id = graphRoute.id,
                    elderName = graphRoute.elderName,
                    startTime = LocalDateTime.parse(graphRoute.startTime),
                    phone = graphRoute.phone,
                )
            }
        }

        // Record
        composable<VolunteerTabRoute.Record> {

        }

        // Settings
        composable<VolunteerTabRoute.Settings> {

        }
    }
}