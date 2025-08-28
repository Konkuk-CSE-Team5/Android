package com.konkuk.hackathon.feature.center.elderregister

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.hackathon.core.common.extension.toDateFormat
import com.konkuk.hackathon.core.common.extension.toPhoneFormat
import com.konkuk.hackathon.core.data.repository.SeniorRepository
import com.konkuk.hackathon.core.network.request.SeniorRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ElderRegisterViewModel @Inject constructor(
    private val seniorRepository: SeniorRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ElderRegisterUiState())
    val uiState = _uiState.asStateFlow()

    private val _isFinish = MutableStateFlow(false)
    val isFinish = _isFinish.asStateFlow()

    fun updateSchedule(newSchedule: ElderRegisterUiState.Schedule) {
        val currentSchedules = _uiState.value.schedules.toMutableList()
        if (currentSchedules.contains(newSchedule)) {
            currentSchedules.remove(newSchedule)
        } else {
            currentSchedules.add(newSchedule)
        }
        _uiState.value = _uiState.value.copy(schedules = currentSchedules)
    }

    fun updateStartDate(newDate: String) {
        _uiState.value = _uiState.value.copy(startDate = newDate)
    }

    fun updateEndDate(newDate: String) {
        _uiState.value = _uiState.value.copy(endDate = newDate)
    }

    fun updateStartTime(newDate: String) {
        _uiState.value = _uiState.value.copy(startTime = newDate)
    }

    fun updateEndTime(newDate: String) {
        _uiState.value = _uiState.value.copy(endTime = newDate)
    }

    fun registerElder() {
        val request = SeniorRequest(
            birthday = _uiState.value.birth.text.toString().toDateFormat(),
            contact = _uiState.value.phoneNumber.text.toString().toPhoneFormat(),
            endDate = _uiState.value.endDate,
            name = _uiState.value.name.text.toString(),
            startDate = _uiState.value.startDate,
            workDays = _uiState.value.schedules.map { it.displayName },
            workEndTime = "${_uiState.value.endTime}:00",
            workStartTime = "${_uiState.value.startTime}:00",
            note = _uiState.value.memo.text.toString(),
        )

        viewModelScope.launch {
            seniorRepository.registerSenior(request)
                .onSuccess {
                    Log.d("ElderRegisterViewModel", "registerElder: 성공")
                    _isFinish.value = true
                }.onFailure {
                    Log.d("ElderRegisterViewModel", "registerElder: 실패 ${it.message}")
                }
        }
    }
}