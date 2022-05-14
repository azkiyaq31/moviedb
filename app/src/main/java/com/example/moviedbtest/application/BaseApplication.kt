package com.example.moviedbtest.application

import android.app.Application
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import com.example.moviedbtest.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class BaseApplication : Application(){

    companion object {
        private lateinit var instance: BaseApplication
        fun getInstance(): BaseApplication {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        instance = this

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

    fun getSharedPreference(): SharedPreferences {
        return getSharedPreferences("moviedb-pref", MODE_PRIVATE)
    }
}
