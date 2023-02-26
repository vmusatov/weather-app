package com.weatherapp.feature_settings_impl.di

import com.weatherapp.feature_settings_api.SettingsRepository
import com.weatherapp.feature_settings_impl.SettingsRepositoryImpl
import org.koin.dsl.module

val settingsModule = module {
    single<SettingsRepository> { SettingsRepositoryImpl() }
}