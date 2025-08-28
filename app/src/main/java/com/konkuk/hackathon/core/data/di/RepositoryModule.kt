package com.konkuk.hackathon.core.data.di

import com.konkuk.hackathon.core.data.repository.AuthRepository
import com.konkuk.hackathon.core.data.repository.CenterHomeRepository
import com.konkuk.hackathon.core.data.repository.VolunteerHomeRepository
import com.konkuk.hackathon.core.data.repositoryimpl.AuthRepositoryImpl
import com.konkuk.hackathon.core.data.repositoryimpl.CenterHomeRepositoryImpl
import com.konkuk.hackathon.core.data.repositoryimpl.VolunteerHomeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindVolunteerHomeRepository(
        volunteerHomeRepositoryImpl: VolunteerHomeRepositoryImpl
    ): VolunteerHomeRepository

    @Binds
    @Singleton
    abstract fun bindCenterHomeRepository(
        centerHomeRepositoryImpl: CenterHomeRepositoryImpl
    ): CenterHomeRepository

}