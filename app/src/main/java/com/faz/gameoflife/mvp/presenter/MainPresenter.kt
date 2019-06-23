package com.faz.gameoflife.mvp.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.faz.gameoflife.mvp.view.MainView
import com.faz.gameoflife.navigation.MainRouter
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(
    private val router: MainRouter
) : MvpPresenter<MainView>() {

    fun openGridScreen() {
        router.openGridScreen()
    }

    fun back() {
        router.exit()
    }

    fun finish() {
        router.finishChain()
    }
}