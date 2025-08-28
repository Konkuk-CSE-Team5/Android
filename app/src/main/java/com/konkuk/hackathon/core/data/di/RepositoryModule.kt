package com.konkuk.hackathon.core.data.di

import com.konkuk.hackathon.core.data.repository.AuthRepository
import com.konkuk.hackathon.core.data.repository.CenterRepository
import com.konkuk.hackathon.core.data.repository.SeniorRepository
import com.konkuk.hackathon.core.data.repository.VolunteerRepository
import com.konkuk.hackathon.core.data.repositoryimpl.AuthRepositoryImpl
import com.konkuk.hackathon.core.data.repositoryimpl.CenterRepositoryImpl
import com.konkuk.hackathon.core.data.repositoryimpl.SeniorRepositoryImpl
import com.konkuk.hackathon.core.data.repositoryimpl.VolunteerRepositoryImpl
import com.konkuk.hackathon.core.data.repository.CenterHomeRepository
import com.konkuk.hackathon.core.data.repositoryimpl.AuthRepositoryImpl
import com.konkuk.hackathon.core.data.repositoryimpl.CenterHomeRepositoryImpl
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
    abstract fun bindVolunteerRepository(
        impl: VolunteerRepositoryImpl,
    ): VolunteerRepository


    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl,
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindSeniorRepository(
        seniorRepositoryImpl: SeniorRepositoryImpl,
    ): SeniorRepository

    @Binds
    @Singleton
    abstract fun bindCenterRepository(
        centerRepositoryImpl: CenterRepositoryImpl,
    ): CenterRepository
    abstract fun bindCenterHomeRepository(
        centerHomeRepositoryImpl: CenterHomeRepositoryImpl
    ): CenterHomeRepository

}