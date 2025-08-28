package com.konkuk.hackathon.core.network.request

import kotlinx.serialization.Serializable

@Serializable
data class ProfileUpdateRequestDto(
    val password : String? = null,
    val name: String? = null,
    val birthday : String? = null,
    val gender : String? = null,
    val contact: String? = null,
)

private fun String?.nullIfBlank() = this?.trim()?.takeIf { it.isNotEmpty() }

fun ProfileUpdateRequestDto.pruned(): ProfileUpdateRequestDto = ProfileUpdateRequestDto(
    password = password.nullIfBlank(),
    name     = name.nullIfBlank(),
    birthday = birthday.nullIfBlank(),
    gender   = gender.nullIfBlank(),
    contact  = contact.nullIfBlank(),
)
