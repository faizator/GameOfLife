package com.faz.gameoflife.mvp.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.faz.gameoflife.mvp.viewdata.GridViewData

const val PLAY_STATE = "play_state"

@StateStrategyType(AddToEndSingleStrategy::class)
interface GridView : MvpView {

    fun setData(data: GridViewData)

    @StateStrategyType(AddToEndSingleStrategy::class, tag = PLAY_STATE)
    fun onPlayState()

    @StateStrategyType(AddToEndSingleStrategy::class, tag = PLAY_STATE)
    fun onStopState()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showPatternSaved(name: String)
}