package com.konkuk.hackathon.feature.signup.center

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CenterSignUpViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(CenterSignUpUiState())
    val uiState: StateFlow<CenterSignUpUiState>
        get() = _uiState.asStateFlow()

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

}