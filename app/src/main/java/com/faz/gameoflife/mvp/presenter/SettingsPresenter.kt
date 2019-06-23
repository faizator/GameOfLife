package com.faz.gameoflife.mvp.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.faz.gameoflife.interactor.SettingsInteractor
import com.faz.gameoflife.mvp.view.SettingsView
import javax.inject.Inject

@InjectViewState
class SettingsPresenter @Inject constructor(
    private val interactor: SettingsInteractor
) : MvpPresenter<SettingsView>() {

    override fun onFirstViewAttach() {
        val widthSize = interactor.getWidthSize()
        val heightSize = interactor.getHeightSize()
        viewState.updateControls(
            interactor.convertSizeToPercent(widthSize),
            interactor.convertSizeToPercent(heightSize)
        )
        viewState.showGridWidth(widthSize)
        viewState.showGridHeight(heightSize)
    }

    fun setWidthAsPercent(progress: Int) {
        val size = interactor.convertPercentToSize(progress)
        interactor.setWidthSize(size)
        viewState.showGridWidth(size)
    }

    fun setHeightAsPercent(progress: Int) {
        val size = interactor.convertPercentToSize(progress)
        interactor.setHeightSize(size)
        viewState.showGridHeight(size)
    }
}