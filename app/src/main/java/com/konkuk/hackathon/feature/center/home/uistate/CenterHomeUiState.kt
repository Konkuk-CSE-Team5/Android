package com.konkuk.hackathon.feature.center.home.uistate

import com.konkuk.hackathon.core.data.model.CallStatusType
import java.time.LocalDate
import java.time.LocalDateTime

data class CenterHomeUiState(
    val weeklyVolunteerStatus: WeeklyVolunteerStatus = WeeklyVolunteerStatus(
        0, 0, 0, 0, 0, 0
    ),
    val attentionRequiredList: List<VolunteersNeedingAttention> = emptyList(),
    val seniorStatusList: List<SeniorStatus> = emptyList()
)

data class WeeklyVolunteerStatus(
    val progressRate: Int,
    val totalCount: Int,
    val completedCount: Int,
    val absentCount: Int,
    val missedCount: Int,
    val pendingCount: Int,
)

data class VolunteersNeedingAttention(
    val date: LocalDate,
    val seniorName: String,
    val status: CallStatusType
)

data class SeniorStatus(
    val seniorId: Int,
    val name: String,
    val age: Int,
    val volunteerName: String,
    val nextSchedule: LocalDateTime,
    val monthlyCalls: Map<String, Int>
)