package com.konkuk.hackathon.feature.volunteer.home.uistate

import com.konkuk.hackathon.core.network.response.VolunteerHomeResponse
import java.time.LocalDate
import java.time.LocalTime

// 최상위 DTO를 UiState로 변환
fun VolunteerHomeResponse.toUiState(): VolunteerHomeUiState {
    return VolunteerHomeUiState(
        seniors = this.seniors.map { it.toUiModel() }
    )
}

// Senior DTO를 SeniorUiModel로 변환
fun VolunteerHomeResponse.Senior.toUiModel(): SeniorUiModel {
    return SeniorUiModel(
        seniorId = this.seniorId,
        name = this.name,
        phone = this.phone,
        age = this.age,
        notes = this.notes ?: "없음",
        nextSchedule = LocalDate.parse(this.nextSchedule), // String -> LocalDate
        schedule = this.schedule.map { it.toUiModel() }
    )
}

// Schedule DTO를 ScheduleUiModel로 변환
fun VolunteerHomeResponse.Senior.Schedule.toUiModel(): ScheduleUiModel {
    return ScheduleUiModel(
        day = this.day,
        startTime = LocalTime.parse(this.startTime), // String -> LocalTime
        endTime = LocalTime.parse(this.endTime) // String -> LocalTime
    )
}