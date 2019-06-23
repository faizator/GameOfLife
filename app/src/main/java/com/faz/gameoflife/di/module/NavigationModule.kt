package com.faz.gameoflife.di.module

import com.faz.gameoflife.navigation.MainRouter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Singleton

@Module
class NavigationModule {

    @Provides
    @Singleton
    fun mainCicerone(router: MainRouter): Cicerone<MainRouter> = Cicerone.create(router)

    @Provides
    @Singleton
    fun mainNavigatorHolder(cicerone: Cicerone<MainRouter>): MainNavigatorHolder = MainNavigatorHolder(cicerone.navigatorHolder)
}

class MainNavigatorHolder(delegate: NavigatorHolder) : NavigatorHolder by delegate