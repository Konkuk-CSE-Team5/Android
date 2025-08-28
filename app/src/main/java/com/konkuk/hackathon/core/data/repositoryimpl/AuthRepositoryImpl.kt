package com.konkuk.hackathon.core.data.repositoryimpl

import com.konkuk.hackathon.core.data.repository.AuthRepository
import com.konkuk.hackathon.core.network.request.LoginRequest
import com.konkuk.hackathon.core.network.request.OrganizationSignUpRequest
import com.konkuk.hackathon.core.network.request.VolunteerSignUpRequest
import com.konkuk.hackathon.core.network.response.base.handleBaseResponse
import com.konkuk.hackathon.core.network.response.base.handleNullableResponse
import com.konkuk.hackathon.core.network.service.AppPreferences
import com.konkuk.hackathon.core.network.service.AuthService
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val appPreferences: AppPreferences,
) : AuthRepository {

    override suspend fun login(username: String, password: String): Result<Unit> =
        runCatching {
            val response = authService.login(
                LoginRequest(
                    username = username,
                    password = password
                )
            ).handleBaseResponse()
            if (response.isSuccess) {
                val accessToken = response.getOrThrow().accessToken
                appPreferences.saveAccessToken(accessToken)
                Result.success(accessToken)
            }
        }


    override suspend fun signUpVolunteer(
        username: String,
        password: String,
        name: String,
        birthday: String,
        gender: String,
        contact: String,
    ): Result<Unit> = runCatching {
        authService.signUpVolunteer(
            VolunteerSignUpRequest(
                username = username,
                password = password,
                name = name,
                birthday = birthday,
                gender = gender,
                contact = contact
            )
        ).handleNullableResponse()
    }

    override suspend fun signUpOrganization(
        username: String,
        password: String,
        name: String,
        manager: String,
        managerContact: String,
    ): Result<Unit> = runCatching {
        authService.signUpOrganization(
            OrganizationSignUpRequest(
                username = username,
                password = password,
                name = name,
                manager = manager,
                managerContact = managerContact
            )
        ).handleNullableResponse()
    }


    override suspend fun logout() {
        appPreferences.clearAccessToken()
    }

    override suspend fun saveAccessToken(token: String) {
        appPreferences.saveAccessToken(token)
    }

    override suspend fun getAccessToken(): String? {
        return appPreferences.getAccessToken()
    }
}