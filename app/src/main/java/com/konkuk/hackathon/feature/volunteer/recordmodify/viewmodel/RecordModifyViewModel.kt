package com.konkuk.hackathon.feature.volunteer.recordmodify.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RecordModifyViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(RecordModifyUiState())
    val uiState: StateFlow<RecordModifyUiState>
        get() = _uiState.asStateFlow()

    fun fetchRecordData(id: Long) {
        // TODO: 초기 데이터 로드
    }

    fun updateHasCalled(hasCalled: Boolean) {
        _uiState.value = _uiState.value.copy(hasCalled = hasCalled)
    }

    fun updateHealthCondition(condition: HealthCondition) {
        _uiState.value = _uiState.value.copy(healthCondition = condition)
    }

    fun updateMindCondition(condition: MindCondition) {
        _uiState.value = _uiState.value.copy(mindCondition = condition)
    }
}