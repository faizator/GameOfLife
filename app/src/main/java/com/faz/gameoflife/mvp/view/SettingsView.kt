package com.faz.gameoflife.mvp.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface SettingsView : MvpView {

    fun showGridWidth(value: Int)

    fun showGridHeight(value: Int)

    fun updateControls(widthAsPercent: Int, heightAsPercent: Int)
}