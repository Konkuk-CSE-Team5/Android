package com.konkuk.hackathon.core.data.repositoryimpl

import com.konkuk.hackathon.core.data.repository.VolunteerRepository
import com.konkuk.hackathon.core.network.response.VolunteerRecordsResponse
import com.konkuk.hackathon.core.network.response.base.handleBaseResponse
import com.konkuk.hackathon.core.network.service.VolunteerService
import javax.inject.Inject

class VolunteerRepositoryImpl @Inject constructor(
    private val volunteerService: VolunteerService,
) : VolunteerRepository {

    override suspend fun getVolunteerRecords(): Result<VolunteerRecordsResponse> = runCatching {
        return volunteerService.getVolunteerRecords().handleBaseResponse()
    }
}