package com.konkuk.hackathon.core.data.repository

interface AuthRepository {
    suspend fun login(username: String, password: String): Result<Unit>
    suspend fun signUpVolunteer(
        username: String,
        password: String,
        name: String,
        birthday: String,
        gender: String,
        contact: String
    ): Result<Unit>
    suspend fun signUpOrganization(
        username: String,
        password: String,
        name: String,
        manager: String,
        managerContact: String
    ): Result<Unit>
    suspend fun logout()
    suspend fun saveAccessToken(token: String)
    suspend fun getAccessToken(): String?
}