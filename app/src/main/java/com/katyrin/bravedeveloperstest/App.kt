package com.katyrin.bravedeveloperstest

import android.app.Application
import com.katyrin.bravedeveloperstest.di.application
import com.katyrin.bravedeveloperstest.di.database
import com.katyrin.bravedeveloperstest.di.network
import com.katyrin.bravedeveloperstest.di.searchModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, searchModule, network, database))
        }
    }
}