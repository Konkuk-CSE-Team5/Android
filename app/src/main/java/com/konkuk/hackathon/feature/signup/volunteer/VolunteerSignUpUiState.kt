package com.konkuk.hackathon.feature.signup.volunteer

import androidx.compose.foundation.text.input.TextFieldState

data class VolunteerSignUpUiState(
    val idState: TextFieldState = TextFieldState(),
    val passwordState: TextFieldState = TextFieldState(),
    val nameState: TextFieldState = TextFieldState(),
    val birthState: TextFieldState = TextFieldState(),
    val gender: Gender = Gender.NONE,
    val isAllTermsAccepted: Boolean = false,
    val isPrivacyTermsAccepted: Boolean = false,
) {
    val idValid: Boolean
        get() = idState.text.length in 4..20
    val passwordValid: Boolean
        get() = passwordState.text.length in 8..20
    val nameValid: Boolean
        get() = nameState.text.isNotEmpty()
    val genderValid: Boolean
        get() = gender != Gender.NONE
    val birthValid: Boolean
        get() = birthState.text.length == 8
                && birthState.text.all { it.isDigit() }
                && birthState.text.toString().toInt() <= 20240000
                && birthState.text.toString().toInt() >= 19000101
}

enum class Gender(val label: String) {
    MALE("남성"),
    FEMALE("여성"),
    NONE("선택 안함")
}
