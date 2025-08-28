package com.konkuk.hackathon.core.network.di

import com.konkuk.hackathon.core.data.repository.CenterInfoRepository
import com.konkuk.hackathon.core.data.repository.ProfileRepository
import com.konkuk.hackathon.core.network.service.SettingService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingModule {

    @Provides
    @Singleton
    fun provideSettingService(retrofit: Retrofit): SettingService {
     return retrofit.create(SettingService::class.java)
    }

    @Provides
    @Singleton
    fun provideCenterInfoRepository(settingService: SettingService): CenterInfoRepository {
        return CenterInfoRepository(settingService)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(settingService: SettingService): ProfileRepository {
        return ProfileRepository(settingService)
    }

}