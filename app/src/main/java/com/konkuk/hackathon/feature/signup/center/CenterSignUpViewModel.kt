package com.konkuk.hackathon.feature.signup.center

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.hackathon.core.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CenterSignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(CenterSignUpUiState())
    val uiState: StateFlow<CenterSignUpUiState>
        get() = _uiState.asStateFlow()

    private val _signUpState = MutableStateFlow(SignUpState.IDLE)
    val signUpState: StateFlow<SignUpState>
        get() = _signUpState.asStateFlow()
    private var _errorMessage: String? = null
    val errorMessage: String?
        get() = _errorMessage

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

    fun signUp() {
        viewModelScope.launch {
            authRepository.signUpOrganization(
                username = _uiState.value.idState.text.toString(),
                password = _uiState.value.passwordState.text.toString(),
                name = _uiState.value.nameState.text.toString(),
                manager = _uiState.value.representativeState.text.toString(),
                managerContact = _uiState.value.phoneNumberState.text.toString(),
            ).onSuccess {
                _signUpState.value = SignUpState.SUCCESS
            }.onFailure {
                _signUpState.value = SignUpState.ERROR
                _errorMessage = it.message
            }
        }
    }
}

enum class SignUpState {
    IDLE,
    SUCCESS,
    ERROR
}