package com.konkuk.hackathon.core.navigation.center

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.konkuk.hackathon.feature.center.setting.screen.CenterInfoScreen
import com.konkuk.hackathon.feature.center.setting.screen.CenterSettingScreen

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

        }

        // Record
        composable<CenterTabRoute.Register> {

        }

        // Settings
        composable<CenterTabRoute.Settings> {
            CenterSettingScreen(
                padding = padding,
                onClickModify = {navController.navigate(CenterRoute.CenterInfoModify)},
            )
        }

        composable<CenterRoute.CenterInfoModify> {
            CenterInfoScreen(
                padding = padding,
                onBackClick = { navController.popBackStack() },
                onClickEdit = { navController.navigate(CenterTabRoute.Settings) }
            )
        }
    }
}