package com.konkuk.hackathon.feature.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.hackathon.core.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState>
        get() = _uiState.asStateFlow()

    private val _uiEvent = Channel<LoginUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun updateLoginType(type: LoginType) {
        _uiState.value = _uiState.value.copy(loginType = type)
    }

    fun login() {
        viewModelScope.launch {
            val userName = _uiState.value.idState.text.toString()
            val password = _uiState.value.passwordState.text.toString()
            val type = when(_uiState.value.loginType) {
                LoginType.VOLUNTEER -> "VOL"
                LoginType.CENTER -> "ORG"
            }

            authRepository.login(userName, password, type)
                .onSuccess {
                    when (uiState.value.loginType) {
                        LoginType.VOLUNTEER -> _uiEvent.send(LoginUiEvent.NavigateToVolunteerMain)
                        LoginType.CENTER -> _uiEvent.send(LoginUiEvent.NavigateToCenterMain)
                    }
                }
                .onFailure { error ->
                    Log.d("LoginViewModel", "login: ${error.message}")
                }

        }
    }

}