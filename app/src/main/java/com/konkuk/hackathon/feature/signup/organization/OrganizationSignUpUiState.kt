package com.konkuk.hackathon.feature.signup.organization

import androidx.compose.foundation.text.input.TextFieldState

data class OrganizationSignUpUiState(
    val idState: TextFieldState = TextFieldState(),
    val passwordState: TextFieldState = TextFieldState(),
    val nameState: TextFieldState = TextFieldState(),
    val representativeState: TextFieldState = TextFieldState(),
    val phoneNumberState: TextFieldState = TextFieldState(),
    val isAllTermsAccepted: Boolean = false,
    val isPrivacyTermsAccepted: Boolean = false,
) {
    val idValid: Boolean
        get() = idState.text.length in 4..20
    val passwordValid: Boolean
        get() = passwordState.text.length in 8..20
    val nameValid: Boolean
        get() = nameState.text.isNotEmpty()
    val representativeValid: Boolean
        get() = representativeState.text.isNotEmpty()
    val phoneNumberValid: Boolean
        get() = phoneNumberState.text.length == 11 && phoneNumberState.text.all { it.isDigit() }

}

enum class Gender(val label: String) {
    MALE("남성"),
    FEMALE("여성"),
    NONE("선택 안함")
}
