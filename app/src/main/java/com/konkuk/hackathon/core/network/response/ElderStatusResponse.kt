package com.konkuk.hackathon.core.network.response


import com.konkuk.hackathon.core.data.model.CallStatusType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ElderStatusResponse(
    @SerialName("matchingStatus")
    val matchingStatus: String,
    @SerialName("records")
    val records: List<Record>,
    @SerialName("seniorId")
    val seniorId: Int,
    @SerialName("seniorName")
    val seniorName: String,
    @SerialName("summary")
    val summary: Summary,
    @SerialName("volunteerName")
    val volunteerName: String
) {
    @Serializable
    data class Record(
        @SerialName("date")
        val date: String,
        @SerialName("duration")
        val duration: String,
        @SerialName("recordId")
        val recordId: Int,
        @SerialName("status")
        val status: CallStatusType
    )

    @Serializable
    data class Summary(
        @SerialName("totalCalls")
        val totalCalls: Int,
        @SerialName("totalDuration")
        val totalDuration: String
    )
}