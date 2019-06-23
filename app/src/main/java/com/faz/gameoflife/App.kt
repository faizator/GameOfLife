package com.faz.gameoflife

import android.app.Application
import com.faz.gameoflife.di.component.AppComponent
import com.faz.gameoflife.di.component.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.create()
        ensureDatabaseCreated()
    }

    private fun ensureDatabaseCreated() = appComponent.patternDatabase.getPatternDao().getCount()

    companion object {
        lateinit var instance: App
            private set
    }
}