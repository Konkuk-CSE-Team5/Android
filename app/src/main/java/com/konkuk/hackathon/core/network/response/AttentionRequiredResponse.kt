package com.konkuk.hackathon.core.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AttentionRequiredResponse(
    @SerialName("alerts")
    val alerts: List<Alert>
) {
    @Serializable
    data class Alert(
        @SerialName("date")
        val date: String,
        @SerialName("duration")
        val duration: String?,
        @SerialName("recordId")
        val recordId: Int,
        @SerialName("seniorName")
        val seniorName: String,
        @SerialName("status")
        val status: String,
        @SerialName("volunteerName")
        val volunteerName: String
    )
}