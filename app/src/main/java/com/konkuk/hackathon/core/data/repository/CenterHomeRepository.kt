package com.konkuk.hackathon.core.data.repository

import com.konkuk.hackathon.core.network.response.CenterHomeResponse

interface CenterHomeRepository {
    suspend fun getCenterHome(): Result<CenterHomeResponse>
}