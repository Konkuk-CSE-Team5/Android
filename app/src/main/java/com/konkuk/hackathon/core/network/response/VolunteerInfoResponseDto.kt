package com.konkuk.hackathon.core.network.response

import kotlinx.serialization.Serializable

@Serializable
data class VolunteerInfoResponseDto(
    val status : Int,
    val code : Int,
    val message : String,
    val data : VolunteerInfoData
)

@Serializable
data class VolunteerInfoData(
    val profile : VolunteerProfile
)

@Serializable
data class VolunteerProfile(
    val userId : String,
    val birthday : String,
    val gender : String,
    val name : String,
    val phone : String,
)