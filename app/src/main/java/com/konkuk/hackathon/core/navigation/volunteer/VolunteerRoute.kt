package com.konkuk.hackathon.core.navigation.volunteer

import kotlinx.serialization.Serializable
import java.time.LocalDateTime

sealed interface VolunteerRoute {
    @Serializable
    data class HomeGraph(
        val id: Int,
        val elderName: String,
        val startTime: String,
        val phone: String
    ) : VolunteerRoute

    @Serializable
    data object VolInfoModify : VolunteerRoute

    @Serializable
    data object RecordSubmit : VolunteerRoute

    @Serializable
    data class RecordAll(val id: Long) : VolunteerRoute

    @Serializable
    data class RecordModify(val id: Long) : VolunteerRoute
}

sealed interface VolunteerTabRoute : VolunteerRoute {
    @Serializable
    data object Home : VolunteerTabRoute

    @Serializable
    data object Record : VolunteerTabRoute

    @Serializable
    data object Settings : VolunteerTabRoute
}