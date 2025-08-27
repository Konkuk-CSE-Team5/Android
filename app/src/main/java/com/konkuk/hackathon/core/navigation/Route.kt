package com.konkuk.hackathon.core.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Onboarding : Route

    @Serializable
    data object Login : Route

    @Serializable
    data object SignUp : Route

    @Serializable
    data object VolunteerSignUp : Route

    @Serializable
    data object OrganizationSignUp : Route

    @Serializable
    data object HomeGraph : Route

    @Serializable
    data object RecordSubmit : Route


}

sealed interface MainTabRoute : Route {
    // 홈
    @Serializable
    data object Home : MainTabRoute

    // 기록
    @Serializable
    data object Record : MainTabRoute

    // 설정
    @Serializable
    data object Settings : MainTabRoute
}