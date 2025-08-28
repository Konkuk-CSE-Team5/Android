package com.konkuk.hackathon.core.navigation.center

import kotlinx.serialization.Serializable

sealed interface CenterRoute {

    @Serializable
    data object AttentionRequiredVolunteeringNavigation : CenterRoute

    @Serializable
    data object ElderStatusNavigation : CenterRoute

    @Serializable
    data object AttentionRequiredVolunteering : CenterRoute

    @Serializable
    data object ElderStatus

}

sealed interface CenterTabRoute : CenterRoute {
    @Serializable
    data object Home : CenterTabRoute

    @Serializable
    data object Register : CenterTabRoute

    @Serializable
    data object Settings : CenterTabRoute
}