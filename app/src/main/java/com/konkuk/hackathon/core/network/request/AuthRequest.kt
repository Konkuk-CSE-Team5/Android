package com.konkuk.hackathon.core.network.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    @SerialName("username") val username: String,
    @SerialName("password") val password: String
)

@Serializable
data class VolunteerSignUpRequest(
    @SerialName("username") val username: String,
    @SerialName("password") val password: String,
    @SerialName("name") val name: String,
    @SerialName("birthday") val birthday: String,
    @SerialName("gender") val gender: String,
    @SerialName("contact") val contact: String
)

@Serializable
data class OrganizationSignUpRequest(
    @SerialName("username") val username: String,
    @SerialName("password") val password: String,
    @SerialName("name") val name: String,
    @SerialName("manager") val manager: String,
    @SerialName("managerContact") val managerContact: String
)