package com.konkuk.hackathon.core.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostCodeResponse(
    @SerialName("name")
    val name: String,
    @SerialName("next_schedule")
    val nextSchedule: String,
    @SerialName("notes")
    val notes: String?,
    @SerialName("schedule")
    val schedule: List<Schedule>
) {
    @Serializable
    data class Schedule(
        @SerialName("day")
        val day: String,
        @SerialName("time")
        val time: String
    )
}