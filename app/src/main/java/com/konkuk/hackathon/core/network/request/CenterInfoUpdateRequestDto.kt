package com.konkuk.hackathon.core.network.request

import kotlinx.serialization.Serializable

@Serializable
data class CenterInfoUpdateRequestDto(
    val password : String? = null,
    val name: String? = null,
    val manager: String? = null,
    val managerContact: String? = null,
)

private fun String?.nullIfBlank() = this?.trim()?.takeIf { it.isNotEmpty() }

fun CenterInfoUpdateRequestDto.pruned() = CenterInfoUpdateRequestDto(
    password = password.nullIfBlank(),
    name = name.nullIfBlank(),
    manager = manager.nullIfBlank(),
    managerContact = managerContact.nullIfBlank(),
)