package com.faz.gameoflife.di.component

import com.faz.gameoflife.di.module.ContextModule
import com.faz.gameoflife.di.module.MainNavigatorHolder
import com.faz.gameoflife.di.module.NavigationModule
import com.faz.gameoflife.di.module.StorageModule
import com.faz.gameoflife.mvp.presenter.GridPresenter
import com.faz.gameoflife.mvp.presenter.MainPresenter
import com.faz.gameoflife.mvp.presenter.PatternSelectPresenter
import com.faz.gameoflife.mvp.presenter.SettingsPresenter
import com.faz.gameoflife.storage.PatternDatabase
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ContextModule::class,
        StorageModule::class,
        NavigationModule::class
    ]
)
interface AppComponent {

    val mainNavigationHolder: MainNavigatorHolder

    val mainPresenter: MainPresenter

    val gridPresenter: GridPresenter

    val settingsPresenter: SettingsPresenter

    val patternSelectPresenter: PatternSelectPresenter

    val patternDatabase: PatternDatabase
}