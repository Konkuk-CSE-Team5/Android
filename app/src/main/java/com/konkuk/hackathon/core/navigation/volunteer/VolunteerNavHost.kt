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
            RecordScreen(
                padding = padding,
                navigateToRecordModify = { navController.navigate(VolunteerRoute.RecordModify) }
            )
        }

        composable<VolunteerRoute.RecordModify> { navBackStackEntry ->
            val id = navBackStackEntry.toRoute<VolunteerRoute.RecordModify>().id
            RecordModifyScreen(
                id = id,
                popBackStack = { navController.popBackStack() }
            )
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
                navigateToRecordModify = { navController.navigate(VolunteerRoute.RecordModify(it)) }
            )
        }
    }
}