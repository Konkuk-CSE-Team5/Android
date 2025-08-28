package com.konkuk.hackathon.core.data.repository

import com.konkuk.hackathon.core.network.response.CenterHomeResponse
import com.konkuk.hackathon.core.network.response.ElderStatusResponse

interface CenterHomeRepository {
    suspend fun getCenterHome(): Result<CenterHomeResponse>

    suspend fun getElderStatus(elderId: Int): Result<ElderStatusResponse>
}