package com.faz.gameoflife.navigation

import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRouter @Inject constructor() : Router() {

    fun openGridScreen() = navigateTo(Screens.GridScreen())

    fun openSettingsScreen() = navigateTo(Screens.SettingsScreen())

    fun openPatternSelectScreen() = navigateTo(Screens.PatternSelectScreen())
}