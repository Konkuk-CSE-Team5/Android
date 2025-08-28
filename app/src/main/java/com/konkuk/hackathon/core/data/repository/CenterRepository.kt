package com.konkuk.hackathon.core.data.repository

import com.konkuk.hackathon.core.network.response.SeniorDetailResponse
import com.konkuk.hackathon.core.network.response.SeniorsResponse

interface CenterRepository {
    suspend fun getSeniors(): Result<SeniorsResponse>
    suspend fun getSeniorDetail(seniorId: Long): Result<SeniorDetailResponse>
}