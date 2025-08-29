package com.konkuk.hackathon.feature.volunteer.record.viewmodel

import android.R.attr.name
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.hackathon.core.data.repository.VolunteerRepository
import com.konkuk.hackathon.core.network.response.SeniorItem
import com.konkuk.hackathon.core.network.response.VolunteerRecordsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.NonCancellable.isCompleted
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val volunteerRepository: VolunteerRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(RecordUiState())
    val uiState: StateFlow<RecordUiState> = _uiState
        .onStart {
            fetchRecordList()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = RecordUiState(),
        )

    private fun fetchRecordList() {
        // TODO: 초기 데이터 로드
        viewModelScope.launch {
            volunteerRepository.getVolunteerRecords()
                .onSuccess { response ->
                    _uiState.update {
                        Log.d("RecordViewModel", "fetchRecordList: $response")
                        response.toUiState()
                    }
                }
                .onFailure {
                    Log.d("RecordViewModel", "fetchRecordList: ${it.message}")
                }
        }
    }
}

fun VolunteerRecordsResponse.toUiState() = RecordUiState(
    elders = seniors.map { it.toElder() }
)

fun SeniorItem.toElder() = Elder(
    id = this.matchingId,
    name = this.seniorName,
    type = if (this.status == "PENDING") ElderType.ONGOING else ElderType.COMPLETED,
    callCount = this.summary.totalCalls,
    totalTime = this.summary.totalDuration,
    records = this.records
        .filter { it.duration != null }
        .map { recordDto ->
            CallRecord(
                id = recordDto.recordId,
                time = recordDto.dateTime ?: "",
                duration = recordDto.duration ?: "0:00:00",
                recordType = when (recordDto.status) {
                    "COMPLETE" -> RecordType.SUCCESS
                    "PENDING" -> RecordType.CALL_NOT_MADE
                    "NOT_CONDUCTED" -> RecordType.CALL_NOT_MADE
                    else -> RecordType.ABSENCE
                }
            )
        }
)