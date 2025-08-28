package com.konkuk.hackathon.core.data.repositoryimpl

import com.konkuk.hackathon.core.data.repository.RecordRepository
import com.konkuk.hackathon.core.network.response.RecordDetailResponse
import com.konkuk.hackathon.core.network.response.base.handleBaseResponse
import com.konkuk.hackathon.core.network.service.RecordService
import javax.inject.Inject

class RecordRepositoryImpl @Inject constructor(
    private val recordService: RecordService,
) : RecordRepository {
    override suspend fun getRecordDetail(recordId: Long): Result<RecordDetailResponse> =
        runCatching {
            return recordService.getRecordDetail(recordId).handleBaseResponse()
        }
}