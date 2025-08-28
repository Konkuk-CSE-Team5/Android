package com.konkuk.hackathon.core.data.repository

import com.konkuk.hackathon.core.network.response.PostCodeResponse
import com.konkuk.hackathon.core.network.response.VolunteerHomeResponse

interface VolunteerHomeRepository {
    suspend fun getVolunteerHome(): Result<VolunteerHomeResponse>

    suspend fun postCode(code: Int): Result<PostCodeResponse>
}