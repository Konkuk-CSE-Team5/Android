package com.konkuk.hackathon.feature.volunteer.home.uistate

import java.time.LocalDate
import java.time.LocalTime

// 홈 화면 전체의 상태
data class VolunteerHomeUiState(
    val seniors: List<SeniorUiModel> = emptyList()
)

// 개별 어르신 정보 UI 모델
data class SeniorUiModel(
    val seniorId: Int,
    val name: String,
    val phone: String,
    val age: Int,
    val notes: String,
    val nextSchedule: LocalDate, // String -> LocalDate
    val schedule: List<ScheduleUiModel>
)

// 스케줄 정보 UI 모델
data class ScheduleUiModel(
    val day: String,
    val startTime: LocalTime, // String -> LocalTime
    val endTime: LocalTime // String -> LocalTime
)