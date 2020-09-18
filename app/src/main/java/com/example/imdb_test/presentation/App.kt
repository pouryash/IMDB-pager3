package com.example.imdb_test.presentation

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.imdb_test.di.appModule
import com.example.imdb_test.di.netModule
import com.example.imdb_test.di.repoModule
import org.koin.android.ext.koin.androidContext

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin()
    }

    fun startKoin() {
        org.koin.core.context.startKoin {
            androidContext(this@App)

            modules(listOf(netModule, appModule, repoModule))
        }
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

    }
}