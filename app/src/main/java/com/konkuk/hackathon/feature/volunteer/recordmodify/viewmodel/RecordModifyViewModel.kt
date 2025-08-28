package com.konkuk.hackathon.feature.volunteer.recordmodify.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.hackathon.core.data.repository.VolunteerRepository
import com.konkuk.hackathon.core.network.request.UpdateRecordRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordModifyViewModel @Inject constructor(
    private val volunteerRepository: VolunteerRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(RecordModifyUiState())
    val uiState: StateFlow<RecordModifyUiState>
        get() = _uiState.asStateFlow()

    fun fetchRecordData(id: Long) {
        viewModelScope.launch {
            volunteerRepository.getVolunteerRecordUpdateForm(id)
                .onSuccess { response ->
                    val callTimes = response.callHistory.map { history ->
                        RecordModifyUiState.CallTime(
                            time = history.dateTime,
                            duration = history.callTime
                        )
                    }
                    
                    _uiState.value = _uiState.value.copy(
                        name = response.name,
                        callTimes = callTimes,
                        hasCalled = response.status == "COMPLETE",
                        healthCondition = mapHealthStatus(response.health),
                        mindCondition = mapMentalityStatus(response.mentality),
                        memo = _uiState.value.memo.apply { edit { replace(0, length, response.opinion) } }
                    )
                }
                .onFailure {
                    Log.d("RecordModifyViewModel", "fetchRecordData: ${it.message}")
                }
        }
    }

    fun patchRecordData(id: Long) {
        viewModelScope.launch {
            val currentState = _uiState.value
            val request = UpdateRecordRequest(
                status = if (currentState.hasCalled) "COMPLETE" else "ABSENT",
                health = mapToHealthValue(currentState.healthCondition),
                mentality = mapToMentalityValue(currentState.mindCondition),
                opinion = currentState.memo.text.toString()
            )
            
            volunteerRepository.updateVolunteerRecord(id, request)
                .onSuccess {
                    Log.d("RecordModifyViewModel", "patchRecordData: 성공")
                }
                .onFailure {
                    Log.d("RecordModifyViewModel", "patchRecordData: ${it.message}")
                }
        }
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
    
    private fun mapHealthStatus(health: String): HealthCondition {
        return when (health) {
            "GOOD" -> HealthCondition.GOOD
            "NORMAL" -> HealthCondition.SOSO
            "BAD" -> HealthCondition.BAD
            else -> HealthCondition.GOOD
        }
    }
    
    private fun mapMentalityStatus(mentality: String): MindCondition {
        return when (mentality) {
            "GOOD" -> MindCondition.GOOD
            "NORMAL" -> MindCondition.SOSO
            "BAD" -> MindCondition.BAD
            else -> MindCondition.GOOD
        }
    }
    
    private fun mapToHealthValue(condition: HealthCondition): String {
        return when (condition) {
            HealthCondition.GOOD -> "GOOD"
            HealthCondition.SOSO -> "NORMAL"
            HealthCondition.BAD -> "BAD"
        }
    }
    
    private fun mapToMentalityValue(condition: MindCondition): String {
        return when (condition) {
            MindCondition.GOOD -> "GOOD"
            MindCondition.SOSO -> "NORMAL"
            MindCondition.BAD -> "BAD"
        }
    }
}