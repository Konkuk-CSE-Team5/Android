package com.konkuk.hackathon.feature.center.register.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.hackathon.core.data.repository.CenterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val centerRepository: CenterRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchSeniors()
    }

    private fun fetchSeniors() {
        viewModelScope.launch {
            centerRepository.getSeniors()
                .onSuccess { response ->
                    val elders = response.seniors.map { senior ->
                        RegisterUiState.Elder(
                            id = senior.seniorId,
                            name = senior.name,
                            age = senior.age,
                            code = senior.code,
                            status = mapMatchingStatus(senior.matchingStatus)
                        )
                    }
                    _uiState.value = _uiState.value.copy(elders = elders)
                }
                .onFailure {
                    Log.d("RegisterViewModel", "fetchSeniors: ${it.message}")
                }
        }
    }

    private fun mapMatchingStatus(status: String): RegisterUiState.ElderStatus {
        return when (status) {
            "PENDING" -> RegisterUiState.ElderStatus.MATCHING
            "ACTIVE" -> RegisterUiState.ElderStatus.SUCCESS
            else -> RegisterUiState.ElderStatus.MATCHING
        }
    }
}