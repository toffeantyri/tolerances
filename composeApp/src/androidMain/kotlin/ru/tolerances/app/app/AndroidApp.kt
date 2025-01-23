package ru.tolerances.app.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import ru.tolerances.app.di.initKoin

class AndroidApp : Application() {

    companion object {
        lateinit var INSTANCE: AndroidApp
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        initKoin {
            androidContext(applicationContext)
        }
    }
}