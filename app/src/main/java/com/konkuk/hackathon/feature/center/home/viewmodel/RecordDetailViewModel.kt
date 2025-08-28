package com.konkuk.hackathon.feature.center.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.hackathon.core.data.model.HealthStatusType
import com.konkuk.hackathon.core.data.repository.CenterHomeRepository
import com.konkuk.hackathon.core.data.repository.RecordRepository
import com.konkuk.hackathon.feature.volunteer.recordmodify.viewmodel.MindCondition
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordDetailViewModel @Inject constructor(
    private val recordRepository: RecordRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(RecordDetailUiState())
    val uiState = _uiState.asStateFlow()

    fun loadRecordDetail(recordId: Long) {
        viewModelScope.launch {
            recordRepository.getRecordDetail(recordId)
                .onSuccess { response ->
                    _uiState.value = _uiState.value.copy(
                        name = response.seniorName,
                        status = RDStatus.valueOf(response.status),
                        volunteerName = response.volunteerName,
                        callTime = response.callDateTime,
                        callDuration = response.totalCallTime,
                        healthStatus = when (response.health) {
                            "GOOD" -> HealthStatusType.GOOD
                            "BAD" -> HealthStatusType.BAD
                            else -> HealthStatusType.NORMAL
                        },
                        mindCondition = when (response.metality) {
                            "GOOD" -> MindCondition.GOOD
                            "BAD" -> MindCondition.BAD
                            else -> MindCondition.SOSO
                        },
                        memo = response.opinion
                    )
                }
                .onFailure { exception ->
                    Log.e("RecordDetailViewModel", "loadRecordDetail: ${exception.message}")
                }
        }
    }

}

data class RecordDetailUiState(
    val name: String = "",
    val status: RDStatus = RDStatus.ABSENT,
    val volunteerName: String = "",
    val callTime: String = "",
    val callDuration: String = "",
    val healthStatus: HealthStatusType = HealthStatusType.GOOD,
    val mindCondition: MindCondition = MindCondition.GOOD,
    val memo: String = "",
)

enum class RDStatus(val status: String) {
    COMPLETE("COMPLETE"), ABSENT("ABSENT"),
}