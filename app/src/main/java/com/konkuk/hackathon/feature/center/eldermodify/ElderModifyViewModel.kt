package com.konkuk.hackathon.feature.center.eldermodify

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.hackathon.core.data.repository.SeniorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ElderModifyViewModel @Inject constructor(
    private val seniorRepository: SeniorRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ElderModifyUiState())
    val uiState = _uiState.asStateFlow()

    fun updateSchedule(newSchedule: ElderModifyUiState.Schedule) {
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

    fun fetchSeniorInfo(id: Long) {
        viewModelScope.launch {
            seniorRepository.getSeniorUpdateForm(id)
                .onSuccess { response ->
                    _uiState.value = _uiState.value.copy(
                        name = androidx.compose.foundation.text.input.TextFieldState(response.name),
                        birth = androidx.compose.foundation.text.input.TextFieldState(response.birthday.replace("-", "")),
                        phoneNumber = androidx.compose.foundation.text.input.TextFieldState(response.contact.replace("-", "")),
                        startDate = response.startDate,
                        endDate = response.endDate,
                        schedules = response.schedule.map { scheduleItem ->
                            ElderModifyUiState.Schedule.valueOf(scheduleItem.day)
                        },
                        startTime = response.schedule.firstOrNull()?.startTime ?: "",
                        endTime = response.schedule.firstOrNull()?.endTime ?: "",
                        memo = androidx.compose.foundation.text.input.TextFieldState(response.notes)
                    )
                }
                .onFailure {
                    // TODO: 에러 처리
                }
        }
    }
}