package com.konkuk.hackathon.feature.center.home.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.hackathon.core.data.repository.CenterHomeRepository
import com.konkuk.hackathon.feature.center.home.uistate.ElderStatusUiState
import com.konkuk.hackathon.feature.center.home.uistate.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ElderStatusViewModel @Inject constructor(
    private val centerHomeRepository: CenterHomeRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ElderStatusUiState())
    val uiState = _uiState.asStateFlow()

    fun getElderStatus(elderId: Int) {
        viewModelScope.launch {
            centerHomeRepository.getElderStatus(elderId = elderId)
                .onSuccess {
                    _uiState.value = it.toUiState()
                }
        }
    }
}