package com.weatherapp.feature_settings_api

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    suspend fun getSettingsFlow(): Flow<AppSettings>

    suspend fun getSettings(): AppSettings

    suspend fun saveSettings(settings: AppSettings)
}