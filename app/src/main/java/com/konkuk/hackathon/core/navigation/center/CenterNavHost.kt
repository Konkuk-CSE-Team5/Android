package com.konkuk.hackathon.core.navigation.center

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.konkuk.hackathon.feature.center.setting.screen.CenterInfoScreen
import com.konkuk.hackathon.feature.center.setting.screen.CenterSettingScreen
import androidx.navigation.compose.navigation
import com.konkuk.hackathon.feature.center.home.screen.AttentionRequiredScreen
import com.konkuk.hackathon.feature.center.home.screen.CenterHomeScreen
import com.konkuk.hackathon.feature.center.home.screen.ElderStatusScreen
import com.konkuk.hackathon.feature.center.home.screen.RecordDetailScreen
import com.konkuk.hackathon.feature.center.register.screen.SuccessRegisterScreen

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
                navigateToElderStatus = { navController.navigate(CenterRoute.ElderStatusNavigation) },
                navigateToAttentionRequired = { navController.navigate(CenterRoute.AttentionRequiredNavigation) })
        }

        navigation<CenterRoute.ElderStatusNavigation>(
            CenterRoute.ElderStatus
        ) {
            composable<CenterRoute.ElderStatus> {
                ElderStatusScreen(
                    padding = padding,
                    popBackStack = { navController.popBackStack() },
                    navigateToRecordDetail = { navController.navigate(CenterRoute.RecordDetail) })
            }
            composable<CenterRoute.RecordDetail> {
                RecordDetailScreen(
                    padding = padding,
                    popBackStack = { navController.popBackStack() },
                )
            }
        }

        navigation<CenterRoute.AttentionRequiredNavigation>(
            CenterRoute.AttentionRequired
        ) {
            composable<CenterRoute.AttentionRequired> {
                AttentionRequiredScreen(
                    padding = padding,
                    popBackStack = { navController.popBackStack() })
            }
        }

        // Record
        composable<CenterTabRoute.Register> {

        }

        composable<CenterRoute.SuccessRegister> {
            SuccessRegisterScreen(
                padding = padding,
                inviteCode = "ABCD1234", // 값 받아와야함
                onCheckClick = { navController.navigate(CenterTabRoute.Register) {
                    // 앱의 최상위 시작 목적지까지 모두 제거
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = true
                    }
                    launchSingleTop = true
                    restoreState = false
                } },
                centerName = "행복 복지센터" // 값 받아와야함
            )
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