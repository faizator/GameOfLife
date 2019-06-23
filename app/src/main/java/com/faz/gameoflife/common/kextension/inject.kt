package com.faz.gameoflife.common.kextension

import com.faz.gameoflife.App
import com.faz.gameoflife.di.component.AppComponent

val appComponent get() = App.instance.appComponent

fun <T> inject(func: AppComponent.() -> T) = lazy { appComponent.func() }