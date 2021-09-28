package com.katyrin.bravedeveloperstest

import android.app.Application
import com.katyrin.bravedeveloperstest.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, searchModule, network, database, randomModule))
        }
    }
}