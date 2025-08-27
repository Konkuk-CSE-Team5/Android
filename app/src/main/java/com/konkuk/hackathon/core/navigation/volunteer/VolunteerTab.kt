package com.konkuk.hackathon.core.navigation.volunteer;

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.konkuk.hackathon.R

enum class VolunteerTab(
    @DrawableRes val iconResId: Int,
    @DrawableRes val selectedIconResId: Int,
    val label: String,
    internal val contentDescription: String,
    val route: VolunteerTabRoute,
) {
    HOME(
        iconResId = R.drawable.ic_home_default,
        selectedIconResId = R.drawable.ic_home_select,
        label = "홈",
        contentDescription = "Home Icon",
        route = VolunteerTabRoute.Home,
    ),
    RECORD(
        iconResId = R.drawable.ic_record_default,
        selectedIconResId = R.drawable.ic_record_select,
        label = "등록",
        contentDescription = "Waiting Icon",
        route = VolunteerTabRoute.Record,
    ),
    SETTING(
        iconResId = R.drawable.ic_setting_default,
        selectedIconResId = R.drawable.ic_setting_select,
        label = "설정",
        contentDescription = "Map Icon",
        route = VolunteerTabRoute.Settings,
    ),

    ;

    companion object {
        @Composable
        fun find(predicate: @Composable (VolunteerTabRoute) -> Boolean): VolunteerTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (VolunteerRoute) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}