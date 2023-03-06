package com.weatherapp.feature_settings_impl

import com.weatherapp.core_base.model.PressureUnit
import com.weatherapp.core_base.model.SpeedUnit
import com.weatherapp.core_base.model.TempUnit
import com.weatherapp.feature_settings_api.AppSettings
import com.weatherapp.feature_settings_api.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class SettingsRepositoryImpl : SettingsRepository {

    private val settingsFlow: MutableSharedFlow<AppSettings> = MutableSharedFlow()

    override suspend fun getSettingsFlow(): Flow<AppSettings> {
        return settingsFlow
    }

    override suspend fun getSettings(): AppSettings {
        return AppSettings(
            tempUnit = TempUnit.C,
            pressureUnit = PressureUnit.MBAR,
            speedUnit = SpeedUnit.KMH
        )
    }

    override suspend fun saveSettings(settings: AppSettings) {

        settingsFlow.emit(settings)
    }

    private companion object {
        const val PREF_TEMP_UNIT_CODE = "PREF_TEMP_CODE"
    }
}