package com.konkuk.hackathon.feature.signup.volunteer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.hackathon.core.data.repository.AuthRepository
import com.konkuk.hackathon.feature.signup.Gender
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VolunteerSignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(VolunteerSignUpUiState())
    val uiState: StateFlow<VolunteerSignUpUiState>
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

    fun updateGender(gender: Gender) {
        _uiState.value = _uiState.value.copy(
            gender = gender
        )
    }
    
    fun signUp(
        username: String,
        password: String,
        name: String,
        birthday: String,
        gender: String,
        contact: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        viewModelScope.launch {
            val result = authRepository.signUpVolunteer(
                username = username,
                password = password,
                name = name,
                birthday = birthday,
                gender = gender,
                contact = contact
            )
            if (result.isSuccess) {
                onResult(true, null)
            } else {
                onResult(false, result.exceptionOrNull()?.message)
            }
        }
    }

}