package com.konkuk.hackathon.core.network.service

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface PrefsEntryPoint {
    fun appPreferences(): AppPreferences
}