package com.konkuk.hackathon.core.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VolunteerHomeResponse(
    @SerialName("seniors")
    val seniors: List<Senior>
) {
    @Serializable
    data class Senior(
        @SerialName("name")
        val name: String,
        @SerialName("next_schedule")
        val nextSchedule: String,
        @SerialName("notes")
        val notes: String?,
        @SerialName("phone")
        val phone: String,
        @SerialName("schedule")
        val schedule: List<Schedule>,
        @SerialName("seniorId")
        val seniorId: Int
    ) {
        @Serializable
        data class Schedule(
            @SerialName("day")
            val day: String,
            @SerialName("endTime")
            val endTime: String,
            @SerialName("startTime")
            val startTime: String
        )
    }
}