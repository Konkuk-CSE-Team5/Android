package com.konkuk.hackathon.feature.login

import androidx.compose.foundation.text.input.TextFieldState

data class LoginUiState(
    val idState: TextFieldState = TextFieldState(),
    val passwordState: TextFieldState = TextFieldState(),
    val loginType: LoginType = LoginType.VOLUNTEER,
)

enum class LoginType(val label: String) {
    VOLUNTEER("봉사자"),
    ORGANIZATION("기관")
}
