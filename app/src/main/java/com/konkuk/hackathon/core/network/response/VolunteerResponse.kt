package com.konkuk.hackathon.core.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VolunteerRecordsResponse(
    @SerialName("seniors") val seniors: List<SeniorItem>
)

@Serializable
data class SeniorItem(
    @SerialName("matchingId") val matchingId: Long,
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
    @SerialName("dateTime") val dateTime: String?,
    @SerialName("duration") val duration: String?,
    @SerialName("status") val status: String
)

@Serializable
data class VolunteerRecordDetailResponse(
    @SerialName("matchingId") val seniorId: Long,
    @SerialName("seniorName") val seniorName: String,
    @SerialName("records") val records: List<CallRecordDto>
)

@Serializable
data class VolunteerRecordUpdateFormResponse(
    @SerialName("name") val name: String,
    @SerialName("callHistory") val callHistory: List<CallHistoryItem>,
    @SerialName("status") val status: String,
    @SerialName("health") val health: String,
    @SerialName("mentality") val mentality: String,
    @SerialName("opinion") val opinion: String
)

@Serializable
data class CallHistoryItem(
    @SerialName("dateTime") val dateTime: String,
    @SerialName("callTime") val callTime: String
)

@Serializable
data class SeniorUpdateFormResponse(
    @SerialName("name") val name: String,
    @SerialName("birthday") val birthday: String,
    @SerialName("contact") val contact: String,
    @SerialName("startDate") val startDate: String,
    @SerialName("endDate") val endDate: String,
    @SerialName("schedule") val schedule: List<ScheduleItem>,
    @SerialName("notes") val notes: String
)

@Serializable
data class ScheduleItem(
    @SerialName("day") val day: String,
    @SerialName("startTime") val startTime: String,
    @SerialName("endTime") val endTime: String
)

@Serializable
data class RecordDetailResponse(
    @SerialName("recordId") val recordId: Long,
    @SerialName("status") val status: String,
    @SerialName("seniorName") val seniorName: String,
    @SerialName("volunteerName") val volunteerName: String,
    @SerialName("callDateTime") val callDateTime: String,
    @SerialName("totalCallTime") val totalCallTime: String,
    @SerialName("health") val health: String,
    @SerialName("metality") val metality: String,
    @SerialName("opinion") val opinion: String
)