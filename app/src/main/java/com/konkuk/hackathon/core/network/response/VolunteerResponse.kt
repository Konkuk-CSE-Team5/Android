package com.konkuk.hackathon.core.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VolunteerRecordsResponse(
    @SerialName("seniors") val seniors: List<SeniorItem>
)

@Serializable
data class SeniorItem(
    @SerialName("seniorId") val seniorId: Long,
    @SerialName("seniorName") val seniorName: String,
    @SerialName("status") val status: String,
    @SerialName("summary") val summary: CallSummary,
    @SerialName("records") val records: List<CallRecordDto>
)

@Serializable
data class CallSummary(
    @SerialName("totalCalls") val totalCalls: Int,
    @SerialName("totalDuration") val totalDuration: String
)

@Serializable
data class CallRecordDto(
    @SerialName("recordId") val recordId: Long,
    @SerialName("dateTime") val dateTime: String,
    @SerialName("duration") val duration: String,
    @SerialName("status") val status: String
)