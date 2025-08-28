package com.konkuk.hackathon.feature.login

sealed interface LoginUiEvent {
    data object NavigateToVolunteerMain : LoginUiEvent
    data object NavigateToCenterMain : LoginUiEvent
}