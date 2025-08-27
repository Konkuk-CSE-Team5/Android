package com.konkuk.hackathon.core.navigation.volunteer

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.konkuk.hackathon.core.navigation.Route
import com.konkuk.hackathon.feature.volunteer.setting.screen.UserSettingScreen

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

        }

        // Record
        composable<VolunteerTabRoute.Record> {

        }

        // Settings
        composable<VolunteerTabRoute.Settings> {
            UserSettingScreen(
                onClickModify = {navController.navigate(VolunteerRoute.SettingsGraph)}
            )
        }

        navigation<VolunteerRoute.SettingsGraph>(
            startDestination = VolunteerRoute.VolInfoGraph
        ) {
            composable<VolunteerRoute.VolInfoGraph> {
                UserSettingScreen(
                    onClickModify = {navController.navigate(VolunteerRoute.SettingsGraph)}
                )
            }
        }
    }
}