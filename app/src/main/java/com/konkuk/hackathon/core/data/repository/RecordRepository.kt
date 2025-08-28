package com.konkuk.hackathon.core.data.repository

import com.konkuk.hackathon.core.network.response.RecordDetailResponse

interface RecordRepository {
    suspend fun getRecordDetail(recordId: Long): Result<RecordDetailResponse>
}