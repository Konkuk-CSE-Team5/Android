package com.konkuk.hackathon.feature.center.elderregister

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ElderRegisterViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ElderRegisterUiState())
    val uiState = _uiState.asStateFlow()

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
}