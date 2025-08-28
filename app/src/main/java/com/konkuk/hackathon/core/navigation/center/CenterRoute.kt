package com.konkuk.hackathon.core.navigation.center

import kotlinx.serialization.Serializable

sealed interface CenterRoute {
    @Serializable
    data object CenterInfoModify : CenterRoute

    @Serializable
    data object AttentionRequiredNavigation : CenterRoute

    @Serializable
    data object AttentionRequired : CenterRoute

    @Serializable
    data class ElderStatusNavigation(val id: Int) : CenterRoute

    @Serializable
    data object ElderStatus : CenterRoute

    @Serializable
    data object VolunteerAllRecord : CenterRoute

    @Serializable
    data object RecordDetail : CenterRoute

    @Serializable
    data object SuccessRegister : CenterRoute

    @Serializable
    data class RegisterManagement(val id: Long) : CenterRoute

    @Serializable
    data object ElderRegister : CenterRoute

    @Serializable
    data class ElderModify(val id: Long) : CenterRoute

}

sealed interface CenterTabRoute : CenterRoute {
    @Serializable
    data object Home : CenterTabRoute

    @Serializable
    data object Register : CenterTabRoute

    @Serializable
    data object Settings : CenterTabRoute
}