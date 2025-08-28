package com.konkuk.hackathon.core.data.di

import com.konkuk.hackathon.core.data.repository.VolunteerRepository
import com.konkuk.hackathon.core.data.repositoryimpl.VolunteerRepositoryImpl
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

}