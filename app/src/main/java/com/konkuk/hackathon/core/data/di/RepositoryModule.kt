package com.konkuk.hackathon.core.data.di

import com.konkuk.hackathon.core.data.repository.AuthRepository
import com.konkuk.hackathon.core.data.repository.DummyRepository
import com.konkuk.hackathon.core.data.repositoryimpl.AuthRepositoryImpl
import com.konkuk.hackathon.core.data.repositoryimpl.DummyRepositoryImpl
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
    abstract fun bindDummyRepository(
        dummyRepositoryImpl: DummyRepositoryImpl
    ): DummyRepository
    
    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

}