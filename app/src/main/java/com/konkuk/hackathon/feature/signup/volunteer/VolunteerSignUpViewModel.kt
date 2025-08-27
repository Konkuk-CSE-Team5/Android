package com.konkuk.hackathon.feature.signup.volunteer

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class VolunteerSignUpViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(VolunteerSignUpUiState())
    val uiState: StateFlow<VolunteerSignUpUiState>
        get() = _uiState.asStateFlow()

    val buttonEnabled: Boolean
        get() = with(_uiState.value) {
            idValid && passwordValid && nameValid && genderValid && birthValid && isPrivacyTermsAccepted && isAllTermsAccepted
        }

    fun updateAllTermsAccepted(checked: Boolean) {
        _uiState.value = _uiState.value.copy(
            isAllTermsAccepted = !checked,
        )
    }

    fun updatePrivacyTermsAccepted(checked: Boolean) {
        _uiState.value = _uiState.value.copy(
            isPrivacyTermsAccepted = !checked,
        )
    }

    fun updateGender(gender: Gender) {
        _uiState.value = _uiState.value.copy(
            gender = gender
        )
    }

}