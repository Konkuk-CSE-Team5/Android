package com.konkuk.hackathon.core.navigation;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.compose.runtime.Composable;
import com.konkuk.hackathon.R;

enum class MainTab(
    @DrawableRes val iconResId: Int,
    @DrawableRes val selectedIconResId: Int,
    @StringRes val labelResId: Int,
    internal val contentDescription: String,
    val route: MainTabRoute,
) {
    HOME(
        iconResId = R.drawable.ic_launcher_background,
        selectedIconResId = R.drawable.ic_launcher_background,
        labelResId = R.string.app_name,
        contentDescription = "Home Icon",
        route = MainTabRoute.Home,
    ),
    RECORD(
        iconResId = R.drawable.ic_launcher_background,
        selectedIconResId = R.drawable.ic_launcher_background,
        labelResId = R.string.app_name,
        contentDescription = "Waiting Icon",
        route = MainTabRoute.Record,
    ),
    SETTING(
        iconResId = R.drawable.ic_launcher_background,
        selectedIconResId = R.drawable.ic_launcher_background,
        labelResId = R.string.app_name,
        contentDescription = "Map Icon",
        route = MainTabRoute.Settings,
    ),

    ;

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}