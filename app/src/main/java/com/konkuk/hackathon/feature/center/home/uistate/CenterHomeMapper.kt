package com.konkuk.hackathon.feature.center.home.uistate

import com.konkuk.hackathon.core.data.model.CallStatusType
import com.konkuk.hackathon.core.network.response.CenterHomeResponse
import java.time.LocalDate
import java.time.LocalDateTime

// 최상위 DTO를 UI State로 변환하는 메인 함수
fun CenterHomeResponse.toUiState(): CenterHomeUiState {
    return CenterHomeUiState(
        weeklyVolunteerStatus = this.weeklyVolunteerStatus.toUiModel2(),
        attentionRequiredList = this.volunteersNeedingAttention.map { it.toUiModel2() },
        seniorStatusList = this.seniorStatuses.map { it.toUiModel() }
    )
}

fun CenterHomeResponse.WeeklyVolunteerStatus.toUiModel2(): WeeklyVolunteerStatus {
    return WeeklyVolunteerStatus(
        progressRate = this.progressRate,
        totalCount = this.totalCount,
        completedCount = this.completedCount,
        absentCount = this.absentCount,
        missedCount = this.missedCount,
        pendingCount = this.pendingCount
    )
}

fun CenterHomeResponse.VolunteersNeedingAttention.toUiModel2(): VolunteersNeedingAttention {
    return VolunteersNeedingAttention(
        date = LocalDate.parse(this.date), // String -> LocalDate 변환
        seniorName = this.seniorName,
        status = CallStatusType.valueOf(this.status.uppercase()) // String -> Enum 변환
    )
}

fun CenterHomeResponse.SeniorStatus.toUiModel(): SeniorStatus {
    return SeniorStatus(
        seniorId = this.seniorId,
        name = this.name,
        age = this.age,
        volunteerName = this.volunteerName,
        nextSchedule = this.nextSchedule?.let { LocalDateTime.parse(it) }
            ?: LocalDateTime.now(), // String -> LocalDateTime 변환
        // MonthlyCalls 객체를 Map으로 변환
        monthlyCalls = mapOf(
            "completed" to this.monthlyCalls.completed,
            "target" to this.monthlyCalls.target
        )
    )
}