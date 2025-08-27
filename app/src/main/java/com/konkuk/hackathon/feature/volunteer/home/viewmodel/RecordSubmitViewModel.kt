package com.konkuk.hackathon.feature.volunteer.home.viewmodel

import androidx.lifecycle.ViewModel
import com.konkuk.hackathon.feature.volunteer.home.uistate.HealthState
import com.konkuk.hackathon.feature.volunteer.home.uistate.PerformanceState
import com.konkuk.hackathon.feature.volunteer.home.uistate.PsychologicalState
import com.konkuk.hackathon.feature.volunteer.home.uistate.RecordSubmitUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RecordSubmitViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(RecordSubmitUiState())
    val uiState = _uiState.asStateFlow()

    fun onPerformanceStateSelected(state: PerformanceState) {
        _uiState.update { it.copy(selectedPerformanceState = state) }
    }

    // 건강 상태 버튼 클릭 이벤트
    fun onHealthStateSelected(state: HealthState) {
        _uiState.update { it.copy(selectedHealthState = state) }
    }

    // 심리 상태 버튼 클릭 이벤트
    fun onPsychologicalStateSelected(state: PsychologicalState) {
        _uiState.update { it.copy(selectedPsychologicalState = state) }
    }

    // 의견 텍스트 변경 이벤트
    fun onOpinionChanged(text: String) {
        _uiState.update { it.copy(opinionText = text.take(300)) }
    }


}