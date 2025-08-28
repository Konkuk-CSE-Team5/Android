package com.konkuk.hackathon.core.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeniorsResponse(
    @SerialName("seniors") val seniors: List<CenterSeniorItem>
)

@Serializable
data class CenterSeniorItem(
    @SerialName("seniorId") val seniorId: Long,
    @SerialName("name") val name: String,
    @SerialName("age") val age: Int,
    @SerialName("code") val code: String,
    @SerialName("matchingStatus") val matchingStatus: String
)

@Serializable
data class SeniorDetailResponse(
    @SerialName("seniorProfile") val seniorProfile: SeniorProfile,
    @SerialName("seniorCode") val seniorCode: String,
    @SerialName("matchedVolunteer") val matchedVolunteer: MatchedVolunteer?
)

@Serializable
data class SeniorProfile(
    @SerialName("name") val name: String,
    @SerialName("birthday") val birthday: String,
    @SerialName("contact") val contact: String,
    @SerialName("startDate") val startDate: String,
    @SerialName("endDate") val endDate: String
)

@Serializable
data class MatchedVolunteer(
    @SerialName("name") val name: String,
    @SerialName("lastWorkDay") val lastWorkDay: String?
)