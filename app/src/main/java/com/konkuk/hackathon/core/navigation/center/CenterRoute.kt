package com.konkuk.hackathon.core.navigation.center

import kotlinx.serialization.Serializable

sealed interface CenterRoute {

}

sealed interface CenterTabRoute : CenterRoute {
    @Serializable
    data object Home : CenterTabRoute

    @Serializable
    data object Register : CenterTabRoute

    @Serializable
    data object Settings : CenterTabRoute
}