package com.konkuk.hackathon.core.navigation.center

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions

class CenterNavigator(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination
    val startDestination = CenterTabRoute.Home

    val currentTab: CenterTab?
        @Composable get() = CenterTab.find { tab ->
            currentDestination?.hasRoute(tab::class) == true
        }

    private val clearStackOptions = navOptions {
        popUpTo(0) {
            inclusive = true
        }
        launchSingleTop = true
    }

    fun navigate(tab: CenterTab) {
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
            CenterTab.HOME -> navController.navigate(CenterTabRoute.Home, navOptions)
            CenterTab.REGISTER -> navController.navigate(CenterTabRoute.Register, navOptions)
            CenterTab.SETTING -> navController.navigate(CenterTabRoute.Settings, navOptions)
        }
    }

    @Composable
    fun shouldShowBottomBar() = CenterTab.contains {
        currentDestination?.hasRoute(it::class) == true
    }
}

@Composable
fun rememberCenterNavigator(
    navController: NavHostController = rememberNavController(),
): CenterNavigator = remember(navController) {
    CenterNavigator(navController)
}