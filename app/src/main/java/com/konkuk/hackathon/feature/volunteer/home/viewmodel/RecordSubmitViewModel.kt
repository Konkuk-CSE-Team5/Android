package com.konkuk.hackathon.feature.volunteer.home.viewmodel

import android.app.Application
import android.provider.CallLog
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.hackathon.core.data.model.CallStatusType
import com.konkuk.hackathon.core.data.repository.VolunteerHomeRepository
import com.konkuk.hackathon.feature.volunteer.home.uistate.CallLogData
import com.konkuk.hackathon.feature.volunteer.home.uistate.HealthState
import com.konkuk.hackathon.feature.volunteer.home.uistate.CallPerformanceState
import com.konkuk.hackathon.feature.volunteer.home.uistate.PsychologicalState
import com.konkuk.hackathon.feature.volunteer.home.uistate.RecordSubmitUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class RecordSubmitViewModel @Inject constructor(
    private val application: Application,
    private val volunteerHomeRepository: VolunteerHomeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RecordSubmitUiState())
    val uiState = _uiState.asStateFlow()
    fun fetchCallLogs(startTime: LocalDateTime, phoneNumber: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val startTimeMillis =
                startTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
            val normalizedPhoneNumber = phoneNumber.replace("-", "")

            val context = application.applicationContext
            val callLogList = mutableListOf<CallLogData>()

            val projection = arrayOf(
                CallLog.Calls.NUMBER,
                CallLog.Calls.TYPE,
                CallLog.Calls.DATE,
                CallLog.Calls.DURATION
            )

            val selection =
                "${CallLog.Calls.NUMBER} = ? AND ${CallLog.Calls.DATE} > ? AND ${CallLog.Calls.TYPE} = ?"
            val selectionArgs =
                arrayOf(
                    normalizedPhoneNumber,
                    startTimeMillis.toString(),
                    CallLog.Calls.OUTGOING_TYPE.toString()
                )
            val sortOrder = "${CallLog.Calls.DATE} DESC"

            val cursor = context.contentResolver.query(
                CallLog.Calls.CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                sortOrder
            )

            cursor?.use {
                // ... (데이터 추출 로직은 이전과 동일)
                val numberIndex = it.getColumnIndex(CallLog.Calls.NUMBER)
                val typeIndex = it.getColumnIndex(CallLog.Calls.TYPE)
                val dateIndex = it.getColumnIndex(CallLog.Calls.DATE)
                val durationIndex = it.getColumnIndex(CallLog.Calls.DURATION)

                while (it.moveToNext()) {
                    val number = it.getString(numberIndex)
                    val type = it.getInt(typeIndex)

                    // 1. date (Long) -> LocalDateTime 변환
                    val dateInMillis = it.getLong(dateIndex)
                    val localDateTime = LocalDateTime.ofInstant(
                        Instant.ofEpochMilli(dateInMillis),
                        ZoneId.systemDefault()
                    )

                    // 2. duration (Long) -> "HH:mm:ss" 형식 String 변환
                    val durationInSeconds = it.getLong(durationIndex)
                    val hours = durationInSeconds / 3600
                    val minutes = (durationInSeconds % 3600) / 60
                    val seconds = durationInSeconds % 60
                    val formattedDuration = String.format("%02d:%02d:%02d", hours, minutes, seconds)
                    val callType = if (durationInSeconds == 0L) {
                        CallPerformanceState.ABSENT
                    } else CallPerformanceState.PERFORMED

                    callLogList.add(
                        CallLogData(
                            dateTime = localDateTime,
                            callTime = formattedDuration,
                            callPerformanceState = callType
                        )
                    )
                }
            }
            _uiState.update {
                it.copy(
                    callLogList = callLogList,
                )
            }

        }
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

    fun postRecord(seniorId: Int, isAny: Boolean) {
        viewModelScope.launch {
            volunteerHomeRepository.postRecord(
                uiState.value.selectedHealthState?.name,
                uiState.value.selectedPsychologicalState?.name,
                uiState.value.opinionText,
                seniorId,
                status = if (isAny) CallStatusType.COMPLETE else CallStatusType.ABSENT,
                callList = uiState.value.callLogList
            )
        }
    }
}