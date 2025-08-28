package com.konkuk.hackathon.core.navigation.center

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.konkuk.hackathon.feature.center.home.screen.CenterHomeScreen
import com.konkuk.hackathon.feature.center.home.screen.ElderStatusScreen

@Composable
fun CenterNavHost(
    padding: PaddingValues,
    navigator: CenterNavigator,
) {
    val navController = navigator.navController

    NavHost(
        navController = navController,
        startDestination = navigator.startDestination,
    ) {
        // Home
        composable<CenterTabRoute.Home> {
            CenterHomeScreen(
                padding = padding,
                navigateToElderStatus = { navController.navigate(CenterRoute.ElderStatusNavigation) })
        }

        navigation<CenterRoute.ElderStatusNavigation>(
            CenterRoute.ElderStatus
        ) {
            composable<CenterRoute.ElderStatus> {
                ElderStatusScreen(
                    padding = padding,
                    popBackStack = { navController.popBackStack() },
                    navigateToRecordDetail = {})
            }
        }

        // Record
        composable<CenterTabRoute.Register> {

        }

        // Settings
        composable<CenterTabRoute.Settings> {

        }
    }
}