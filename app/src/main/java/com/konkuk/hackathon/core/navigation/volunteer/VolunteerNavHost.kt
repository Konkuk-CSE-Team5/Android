package com.konkuk.hackathon.core.navigation.volunteer

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.konkuk.hackathon.feature.volunteer.home.screen.RecordSubmitScreen
import com.konkuk.hackathon.feature.volunteer.home.screen.VolunteerHomeScreen
import com.konkuk.hackathon.feature.volunteer.setting.screen.VolunteerInfoScreen
import com.konkuk.hackathon.feature.volunteer.setting.screen.VolunteerSettingsScreen

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
                navigateToRecordSubmit = { navController.navigate(VolunteerRoute.HomeGraph) })
        }

        // Home Nested Graph
        navigation<VolunteerRoute.HomeGraph>(
            startDestination = VolunteerRoute.RecordSubmit
        ) {
            composable<VolunteerRoute.RecordSubmit> {
                RecordSubmitScreen(
                    padding = padding,
                    popBackStack = { navController.popBackStack() })
            }
        }

        // Record
        composable<VolunteerTabRoute.Record> {

        }

        // Settings
        composable<VolunteerTabRoute.Settings> {
            VolunteerSettingsScreen(
                onClickModify = {navController.navigate(VolunteerRoute.VolInfoModify)}
            )
        }

        composable<VolunteerRoute.VolInfoModify> {
            VolunteerInfoScreen(
                onBackClick = { navController.popBackStack() },
                navigateToModify = { navController.navigate(VolunteerTabRoute.Settings) }
            )
        }
    }
}