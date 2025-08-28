package com.konkuk.hackathon.core.network.di

import com.konkuk.hackathon.core.network.service.ApiService
import com.konkuk.hackathon.core.network.service.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create()

    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthService = retrofit.create()

}