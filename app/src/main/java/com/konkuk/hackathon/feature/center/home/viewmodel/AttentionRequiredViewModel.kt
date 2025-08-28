package com.konkuk.hackathon.feature.center.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.hackathon.core.data.repository.CenterHomeRepository
import com.konkuk.hackathon.core.data.repository.VolunteerHomeRepository
import com.konkuk.hackathon.feature.center.home.uistate.AttentionRequiredUiState
import com.konkuk.hackathon.feature.center.home.uistate.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttentionRequiredViewModel @Inject constructor(
    private val volunteerHomeRepository: VolunteerHomeRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(AttentionRequiredUiState())
    val uiState = _uiState.asStateFlow()

    fun getAttention() {
        viewModelScope.launch {
            volunteerHomeRepository.getAttentionRequired().onSuccess { response ->
                _uiState.value = response.toUiState()
            }
                .onFailure {  }
        }
    }
}