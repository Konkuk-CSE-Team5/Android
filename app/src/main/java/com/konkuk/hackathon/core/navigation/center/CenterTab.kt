package com.konkuk.hackathon.core.navigation.center;

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import com.konkuk.hackathon.R

enum class CenterTab(
    @DrawableRes val iconResId: Int,
    @DrawableRes val selectedIconResId: Int,
    val label: String,
    internal val contentDescription: String,
    val route: CenterTabRoute,
) {
    HOME(
        iconResId = R.drawable.ic_home_default,
        selectedIconResId = R.drawable.ic_home_select,
        label = "홈",
        contentDescription = "Home Icon",
        route = CenterTabRoute.Home,
    ),
    REGISTER(
        iconResId = R.drawable.ic_record_default,
        selectedIconResId = R.drawable.ic_record_select,
        label = "등록",
        contentDescription = "Waiting Icon",
        route = CenterTabRoute.Register,
    ),
    SETTING(
        iconResId = R.drawable.ic_setting_default,
        selectedIconResId = R.drawable.ic_setting_select,
        label = "설정",
        contentDescription = "Map Icon",
        route = CenterTabRoute.Settings,
    ),

    ;

    companion object {
        @Composable
        fun find(predicate: @Composable (CenterTabRoute) -> Boolean): CenterTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (CenterRoute) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}