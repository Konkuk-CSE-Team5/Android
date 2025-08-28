package com.konkuk.hackathon.feature.volunteer.recordall

import android.R.attr.duration
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.hackathon.core.data.repository.VolunteerRepository
import com.konkuk.hackathon.feature.volunteer.record.viewmodel.CallRecord
import com.konkuk.hackathon.feature.volunteer.record.viewmodel.RecordType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordAllViewModel @Inject constructor(
    private val volunteerRepository: VolunteerRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(RecordAllUiState())
    val uiState = _uiState.asStateFlow()

    fun loadRecordAll(recordId: Long) {
        viewModelScope.launch {
            volunteerRepository.getVolunteerRecord(recordId)
                .onSuccess { response ->
                    _uiState.value = _uiState.value.copy(
                        name = response.seniorName,
                        records = response.records
                            .filter { it.duration != null }
                            .map { recordDto ->
                                CallRecord(
                                    id = recordDto.recordId,
                                    time = recordDto.dateTime ?: "",
                                    duration = recordDto.duration ?: "",
                                    recordType = when (recordDto.status) {
                                        "COMPLETE" -> RecordType.SUCCESS
                                        "ABSENT" -> RecordType.ABSENCE
                                        else -> RecordType.CALL_NOT_MADE
                                    }
                                )
                            }
                    )
                    Log.d("RecordViewModel", "fetchRecordDetail success: $response")
                }
                .onFailure { exception ->
                    Log.e("RecordViewModel", "fetchRecordDetail: ${exception.message}")
                }
        }
    }
}