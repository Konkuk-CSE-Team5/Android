package com.konkuk.hackathon.core.network.di

import com.konkuk.hackathon.core.network.service.ApiService
import com.konkuk.hackathon.core.network.service.VolunteerService
import com.konkuk.hackathon.core.network.service.AuthService
import com.konkuk.hackathon.core.network.service.CenterService
import com.konkuk.hackathon.core.network.service.SeniorService
import com.konkuk.hackathon.core.network.service.CenterHomeService
import com.konkuk.hackathon.core.network.service.RecordService
import com.konkuk.hackathon.core.network.service.VolunteerHomeService
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
    fun provideVolunteerService(retrofit: Retrofit): VolunteerService = retrofit.create()

    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthService = retrofit.create()

    @Provides
    @Singleton
    fun provideSeniorService(retrofit: Retrofit): SeniorService = retrofit.create()

    @Provides
    @Singleton
    fun provideVolunteerHomeService(retrofit: Retrofit): VolunteerHomeService = retrofit.create()
    
    @Provides
    @Singleton
    fun provideCenterService(retrofit: Retrofit): CenterService = retrofit.create()
    
    @Provides
    @Singleton
    fun provideCenterHomeService(retrofit: Retrofit): CenterHomeService = retrofit.create()
    
    @Provides
    @Singleton
    fun provideRecordService(retrofit: Retrofit): RecordService = retrofit.create()
}