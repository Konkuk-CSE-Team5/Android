package com.konkuk.hackathon.core.navigation.volunteer

import kotlinx.serialization.Serializable

sealed interface VolunteerRoute {
    @Serializable
    data object HomeGraph : VolunteerRoute
    @Serializable
    data object VolInfoModify : VolunteerRoute
    @Serializable
    data object RecordSubmit : VolunteerRoute
}

sealed interface VolunteerTabRoute : VolunteerRoute {
    @Serializable
    data object Home : VolunteerTabRoute

    @Serializable
    data object Record : VolunteerTabRoute

    @Serializable
    data object Settings : VolunteerTabRoute
}