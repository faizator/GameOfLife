package com.faz.gameoflife.di.module

import android.content.Context
import com.faz.gameoflife.App
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class ContextModule {

    @Provides
    @Singleton
    fun context(): Context = App.instance.applicationContext
}