package com.konkuk.hackathon.core.navigation.volunteer

import kotlinx.serialization.Serializable

sealed interface VolunteerRoute {
    @Serializable
    data object SettingsGraph : VolunteerRoute

    @Serializable
    data object VolInfoGraph : VolunteerRoute
}

sealed interface VolunteerTabRoute : VolunteerRoute {
    @Serializable
    data object Home : VolunteerTabRoute

    @Serializable
    data object Record : VolunteerTabRoute

    @Serializable
    data object Settings : VolunteerTabRoute
}