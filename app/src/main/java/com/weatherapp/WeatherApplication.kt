package com.weatherapp

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.weatherapp.core_coroutines.coroutinesModule
import com.weatherapp.core_network.di.networkModule
import com.weatherapp.feature_home.di.homeModule
import com.weatherapp.feature_settings_impl.di.settingsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        startKoin {
            androidContext(this@WeatherApplication)
            modules(coroutinesModule, networkModule, homeModule, settingsModule)
        }
    }
}