package com.konkuk.hackathon.core.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CenterHomeResponse(
    @SerialName("seniorStatuses")
    val seniorStatuses: List<SeniorStatus>,
    @SerialName("volunteersNeedingAttention")
    val volunteersNeedingAttention: List<VolunteersNeedingAttention>,
    @SerialName("weeklyVolunteerStatus")
    val weeklyVolunteerStatus: WeeklyVolunteerStatus,
) {
    @Serializable
    data class VolunteersNeedingAttention(
        @SerialName("date")
        val date: String,
        @SerialName("seniorName")
        val seniorName: String,
        @SerialName("status")
        val status: String,
    )

    @Serializable
    data class SeniorStatus(
        @SerialName("age")
        val age: Int,
        @SerialName("monthlyCalls")
        val monthlyCalls: MonthlyCalls,
        @SerialName("name")
        val name: String,
        @SerialName("nextSchedule")
        val nextSchedule: String?,
        @SerialName("seniorId")
        val seniorId: Int,
        @SerialName("volunteerName")
        val volunteerName: String,
    ) {
        @Serializable
        data class MonthlyCalls(
            @SerialName("completed")
            val completed: Int,
            @SerialName("target")
            val target: Int,
        )
    }

    @Serializable
    data class WeeklyVolunteerStatus(
        @SerialName("absentCount")
        val absentCount: Int,
        @SerialName("completedCount")
        val completedCount: Int,
        @SerialName("missedCount")
        val missedCount: Int,
        @SerialName("pendingCount")
        val pendingCount: Int,
        @SerialName("progressRate")
        val progressRate: Int,
        @SerialName("totalCount")
        val totalCount: Int,
    )
}