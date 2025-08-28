package com.konkuk.hackathon.core.network.response.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    @SerialName("success") val success: Boolean,
    @SerialName("code") val code: Int,
    @SerialName("message") val message: String,
    @SerialName("data") val data: T,
)

fun <T> BaseResponse<T>.handleBaseResponse(): Result<T> =
    when (this.code) {
        in 100..299 -> {
            Result.success(this.data)
        }

        in 400..499 -> {
            Result.failure(Exception("Client error : ${this.message}"))
        }

        in 500..599 -> {
            Result.failure(Exception("Server error : ${this.message}"))
        }

        in 1000..2999 -> {
            Result.success(this.data)
        }

        in 4000..4999 -> {
            Result.failure(Exception("Client error : ${this.message}"))
        }

        in 5000..5999 -> {
            Result.failure(Exception("Server error : ${this.message}"))
        }

        else -> {
            Result.failure(Exception("Unknown error : ${this.message}"))
        }
    }
