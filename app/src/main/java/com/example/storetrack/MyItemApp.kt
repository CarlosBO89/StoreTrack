package com.example.storetrack

import android.app.Application
import com.example.storetrack.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyItemApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyItemApp)
            modules(appModule)
        }
    }
}