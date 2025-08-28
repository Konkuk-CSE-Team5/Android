package com.konkuk.hackathon.core.navigation.center

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.konkuk.hackathon.feature.center.eldermanage.ElderManageScreen
import com.konkuk.hackathon.feature.center.eldermodify.ElderModifyScreen
import com.konkuk.hackathon.feature.center.elderregister.ElderRegisterScreen
import com.konkuk.hackathon.feature.center.home.screen.AttentionRequiredScreen
import com.konkuk.hackathon.feature.center.home.screen.CenterHomeScreen
import com.konkuk.hackathon.feature.center.home.screen.ElderStatusScreen
import com.konkuk.hackathon.feature.center.home.screen.RecordDetailScreen
import com.konkuk.hackathon.feature.center.home.screen.VolunteerAllRecordScreen
import com.konkuk.hackathon.feature.center.register.screen.RegisterScreen
import com.konkuk.hackathon.feature.center.register.screen.SuccessRegisterScreen
import com.konkuk.hackathon.feature.center.setting.screen.CenterInfoScreen
import com.konkuk.hackathon.feature.center.setting.screen.CenterSettingScreen
import com.konkuk.hackathon.feature.center.setting.viewmodel.CenterInfoViewModel

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
                navigateToElderStatus = {
                    navController.navigate(
                        CenterRoute.ElderStatusNavigation(
                            it
                        )
                    )
                },
                navigateToAttentionRequired = { navController.navigate(CenterRoute.AttentionRequiredNavigation) })
        }

        navigation<CenterRoute.ElderStatusNavigation>(
            CenterRoute.ElderStatus
        ) {

            composable<CenterRoute.ElderStatus> {
                val parentEntry = remember(it) {
                    navController.getBackStackEntry<CenterRoute.ElderStatusNavigation>()
                }
                val graphRoute = parentEntry.toRoute<CenterRoute.ElderStatusNavigation>()
                ElderStatusScreen(
                    padding = padding,
                    popBackStack = { navController.popBackStack() },
                    elderId = graphRoute.elderId,
                    navigateToRecordDetail = { navController.navigate(CenterRoute.RecordDetail) },
                    navigateToAllRecord = { navController.navigate(CenterRoute.VolunteerAllRecord) })
            }
            composable<CenterRoute.VolunteerAllRecord> {
                VolunteerAllRecordScreen(
                    padding = padding, popBackStack = { navController.popBackStack() },
                    navigateToRecordDetail = {
                        navController.navigate(CenterRoute.RecordDetail)
                    }
                )
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

        // Register
        composable<CenterTabRoute.Register> {
            RegisterScreen(
                padding = padding,
                navigateToElderManagement = {
                    navController.navigate(
                        CenterRoute.RegisterManagement(it)
                    )
                },
                navigateToElderRegister = { navController.navigate(CenterRoute.ElderRegister) }
            )
        }

        // 어르신 관리
        composable<CenterRoute.RegisterManagement> { navBackStackEntry ->
            val id = navBackStackEntry.toRoute<CenterRoute.RegisterManagement>().id
            ElderManageScreen(
                padding = padding,
                id = id,
                popBackStack = { navController.popBackStack() },
                navigateToElderModify = { navController.navigate(CenterRoute.ElderModify(id)) }
            )
        }

        // 신규 어르신 등록
        composable<CenterRoute.ElderRegister> {
            ElderRegisterScreen(
                padding = padding,
                popBackStack = { navController.popBackStack() }
            )
        }

        // 어르신 수정
        composable<CenterRoute.ElderModify> { navBackStackEntry ->
            val id = navBackStackEntry.toRoute<CenterRoute.ElderModify>().id
            ElderModifyScreen(
                padding = padding,
                id = id,
                popBackStack = { navController.popBackStack() }
            )
        }

        composable<CenterRoute.SuccessElderRegister> {
            SuccessRegisterScreen(
                padding = padding,
                inviteCode = "ABCD1234", // 값 받아와야함
                onCheckClick = { navController.navigate(CenterTabRoute.Register) {
                    // 앱의 최상위 시작 목적지까지 모두 제거
                    popUpTo(navController.graph.
                    findStartDestination().id) {
                        inclusive = true
                    }
                    launchSingleTop = true
                    restoreState = false
                } },
                centerName = "행복 복지센터" // 값 받아와야함
            )
        }

        // Settings
        navigation<CenterRoute.CenterSettingsGraph>(
            startDestination = CenterTabRoute.Settings
        ) {
            composable<CenterTabRoute.Settings> { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(CenterRoute.CenterSettingsGraph)
                }
                val sharedVm: CenterInfoViewModel = hiltViewModel(parentEntry)
                LaunchedEffect(Unit) { sharedVm.loadCenterInfo() }

                CenterSettingScreen(
                    padding = padding,
                    vm = sharedVm, // 같은 뷰모델 공유
                    onClickModify = { navController.navigate(CenterRoute.CenterInfoModify) }
                )
            }

            // Info: 서버 재호출 없이 같은 VM 사용
            composable<CenterRoute.CenterInfoModify> { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(CenterRoute.CenterSettingsGraph)
                }
                val sharedVm: CenterInfoViewModel = hiltViewModel(parentEntry)
        composable<CenterTabRoute.Settings> {
            CenterSettingScreen(
                padding = padding,
                onClickModify = { navController.navigate(CenterRoute.CenterInfoModify) },
            )
        }

                CenterInfoScreen(
                    padding = padding,
                    vm = sharedVm, // 같은 뷰모델 공유
                    onBackClick = { navController.popBackStack() },
                    onClickEdit = { navController.popBackStack() }
                )
            }
        }

    }
}
