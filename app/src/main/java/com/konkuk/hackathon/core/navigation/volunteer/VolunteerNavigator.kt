package com.konkuk.hackathon.core.navigation.volunteer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions

class VolunteerNavigator(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination
    val startDestination = VolunteerTabRoute.Home

    val currentTab: VolunteerTab?
        @Composable get() = VolunteerTab.find { tab ->
            currentDestination?.hasRoute(tab::class) == true
        }

    private val clearStackOptions = navOptions {
        popUpTo(0) {
            inclusive = true
        }
        launchSingleTop = true
    }

    fun navigate(tab: VolunteerTab) {
        val navOptions = navOptions {
            navController.currentDestination?.route?.let {
                popUpTo(it) {
                    inclusive = true
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            VolunteerTab.HOME -> navController.navigate(VolunteerTabRoute.Home, navOptions)
            VolunteerTab.RECORD -> navController.navigate(VolunteerTabRoute.Record, navOptions)
            VolunteerTab.SETTING -> navController.navigate(VolunteerTabRoute.Settings, navOptions)
        }
    }

    @Composable
    fun shouldShowBottomBar() = VolunteerTab.contains {
        currentDestination?.hasRoute(it::class) == true
    }
}

@Composable
fun rememberVolunteerNavigator(
    navController: NavHostController = rememberNavController(),
): VolunteerNavigator = remember(navController) {
    VolunteerNavigator(navController)
}