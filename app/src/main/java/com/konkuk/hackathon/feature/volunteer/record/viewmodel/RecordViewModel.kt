package com.konkuk.hackathon.feature.volunteer.record.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor() : ViewModel() {
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
        _uiState.update {
            RecordUiState(
                elders = listOf(
                    Elder(
                        name = "홍길동",
                        type = ElderType.COMPLETED,
                        callCount = 3,
                        totalTime = "0시간 10분 30초",
                        records = listOf(
                            CallRecord(
                                time = "2024-06-01 14:00",
                                duration = "10분 30초",
                                recordType = RecordType.SUCCESS
                            ),
                            CallRecord(
                                time = "2024-06-02 14:00",
                                duration = "",
                                recordType = RecordType.CALL_NOT_MADE
                            ),
                            CallRecord(
                                time = "2024-06-03 14:00",
                                duration = "00:00",
                                recordType = RecordType.ABSENCE
                            ),
                        ),
                    ),
                    Elder(
                        name = "홍길동",
                        type = ElderType.COMPLETED,
                        callCount = 3,
                        totalTime = "0시간 10분 30초",
                        records = listOf(
                            CallRecord(
                                time = "2024-06-01 14:00",
                                duration = "10분 30초",
                                recordType = RecordType.SUCCESS
                            ),
                            CallRecord(
                                time = "2024-06-02 14:00",
                                duration = "",
                                recordType = RecordType.CALL_NOT_MADE
                            ),
                            CallRecord(
                                time = "2024-06-03 14:00",
                                duration = "00:00",
                                recordType = RecordType.ABSENCE
                            ),
                        ),
                    ),
                    Elder(
                        name = "홍길동",
                        type = ElderType.COMPLETED,
                        callCount = 3,
                        totalTime = "0시간 10분 30초",
                        records = listOf(
                            CallRecord(
                                time = "2024-06-01 14:00",
                                duration = "10분 30초",
                                recordType = RecordType.SUCCESS
                            ),
                            CallRecord(
                                time = "2024-06-02 14:00",
                                duration = "",
                                recordType = RecordType.CALL_NOT_MADE
                            ),
                            CallRecord(
                                time = "2024-06-03 14:00",
                                duration = "00:00",
                                recordType = RecordType.ABSENCE
                            ),
                        ),
                    ),
                    Elder(
                        name = "홍길동",
                        type = ElderType.COMPLETED,
                        callCount = 3,
                        totalTime = "0시간 10분 30초",
                        records = listOf(
                            CallRecord(
                                time = "2024-06-01 14:00",
                                duration = "10분 30초",
                                recordType = RecordType.SUCCESS
                            ),
                            CallRecord(
                                time = "2024-06-02 14:00",
                                duration = "",
                                recordType = RecordType.CALL_NOT_MADE
                            ),
                            CallRecord(
                                time = "2024-06-03 14:00",
                                duration = "00:00",
                                recordType = RecordType.ABSENCE
                            ),
                        ),
                    ),
                )
            )
        }
    }
}