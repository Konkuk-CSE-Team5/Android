package com.konkuk.hackathon.core.navigation.volunteer

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.konkuk.hackathon.feature.volunteer.home.screen.RecordSubmitScreen
import com.konkuk.hackathon.feature.volunteer.home.screen.VolunteerHomeScreen
import com.konkuk.hackathon.feature.volunteer.setting.screen.VolunteerInfoScreen
import com.konkuk.hackathon.feature.volunteer.setting.screen.VolunteerSettingsScreen
import com.konkuk.hackathon.feature.volunteer.record.screen.RecordScreen
import com.konkuk.hackathon.feature.volunteer.recordall.RecordAllScreen
import com.konkuk.hackathon.feature.volunteer.recordmodify.screen.RecordModifyScreen

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
            RecordScreen(
                padding = padding,
                navigateToRecordModify = { navController.navigate(VolunteerRoute.RecordModify) }
            )
        }

        composable<VolunteerRoute.RecordModify> {
            RecordModifyScreen(
                popBackStack = { navController.popBackStack() })
        }

        // Settings
        composable<VolunteerTabRoute.Settings> {
            VolunteerSettingsScreen(
                padding = padding,
                onClickModify = { navController.navigate(VolunteerRoute.VolInfoModify) }
            )
        }

        composable<VolunteerRoute.VolInfoModify> {
            VolunteerInfoScreen(
                padding = padding,
                onBackClick = { navController.popBackStack() },
                onClickEdit = { navController.navigate(VolunteerTabRoute.Settings) }
            )
        }

        composable<VolunteerRoute.RecordAll> { navBackStackEntry ->
            val id = navBackStackEntry.toRoute<VolunteerRoute.RecordAll>().id

            RecordAllScreen(
                id = id,
                padding = padding,
                popBackStack = { navController.popBackStack() },
                navigateToRecordModify = { navController.navigate(VolunteerRoute.RecordModify) }
            )
        }
    }
}