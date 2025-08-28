package com.konkuk.hackathon.feature.signup.volunteer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.hackathon.core.common.extension.toDateFormat
import com.konkuk.hackathon.core.data.repository.AuthRepository
import com.konkuk.hackathon.feature.signup.Gender
import com.konkuk.hackathon.feature.signup.center.SignUpState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VolunteerSignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(VolunteerSignUpUiState())
    val uiState: StateFlow<VolunteerSignUpUiState>
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

    fun updateGender(gender: Gender) {
        _uiState.value = _uiState.value.copy(
            gender = gender
        )
    }

    fun signUp() {
        viewModelScope.launch {
            authRepository.signUpVolunteer(
                username = _uiState.value.idState.text.toString(),
                password = _uiState.value.passwordState.text.toString(),
                name = _uiState.value.nameState.text.toString(),
                birthday = _uiState.value.birthState.text.toString().toDateFormat(),
                gender = _uiState.value.gender.eng,
                contact = _uiState.value.phoneNumberState.text.toString(),
            ).onSuccess {
                _signUpState.value = SignUpState.SUCCESS
            }.onFailure {
                Log.d("SignUp Error", it.message.toString())
                _signUpState.value = SignUpState.ERROR
                _errorMessage = it.message
            }
        }
    }
}