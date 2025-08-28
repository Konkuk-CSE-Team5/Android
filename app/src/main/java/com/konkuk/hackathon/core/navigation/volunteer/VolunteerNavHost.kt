package com.konkuk.hackathon.core.navigation.volunteer

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
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
import com.konkuk.hackathon.feature.volunteer.setting.viewmodel.ProfileViewModel

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
                navigateToRecordSubmit = { p1, p2, p3, p4 ->
                    navController.navigate(
                        VolunteerRoute.HomeGraph(
                            id = p1,
                            elderName = p2,
                            phone = p3,
                            startTime = p4
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
                navigateToRecordModify = { navController.navigate(VolunteerRoute.RecordModify(it)) },
                navigateToRecordAll = { navController.navigate(VolunteerRoute.RecordAll(it)) }
            )
        }

        composable<VolunteerRoute.RecordModify> { navBackStackEntry ->
            val id = navBackStackEntry.toRoute<VolunteerRoute.RecordModify>().id
            RecordModifyScreen(
                id = id,
                paddingValues = padding,
                popBackStack = { navController.popBackStack() }
            )
        }

        // Settings
        navigation<VolunteerRoute.SettingsGraph>(
            startDestination = VolunteerTabRoute.Settings
        ) {
            composable<VolunteerTabRoute.Settings> { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(VolunteerRoute.SettingsGraph)
                }
                val sharedVm: ProfileViewModel = hiltViewModel(parentEntry)
                LaunchedEffect(Unit) { sharedVm.loadProfile() }

                VolunteerSettingsScreen(
                    padding = padding,
                    vm = sharedVm, // 같은 뷰모델 공유
                    onClickModify = { navController.navigate(VolunteerRoute.VolInfoModify) }
                )
            }

            // Info: 서버 재호출 없이 같은 VM 사용
            composable<VolunteerRoute.VolInfoModify> { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(VolunteerRoute.SettingsGraph)
                }
                val sharedVm: ProfileViewModel = hiltViewModel(parentEntry)

                VolunteerInfoScreen(
                    padding = padding,
                    vm = sharedVm, // 같은 뷰모델 공유
                    onBackClick = { navController.popBackStack() },
                    onClickEdit = { navController.popBackStack() }
                )
            }
        }

        composable<VolunteerRoute.RecordAll> { navBackStackEntry ->
            val id = navBackStackEntry.toRoute<VolunteerRoute.RecordAll>().id
            Log.d("VolunteerNavHost", "RecordAll id: $id")

            RecordAllScreen(
                id = id,
                padding = padding,
                popBackStack = { navController.popBackStack() },
                navigateToRecordModify = { navController.navigate(VolunteerRoute.RecordModify(it)) }
            )
        }
    }
}