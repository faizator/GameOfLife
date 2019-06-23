package com.faz.gameoflife.mvp.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.faz.gameoflife.mvp.viewdata.PatternViewData

@StateStrategyType(AddToEndSingleStrategy::class)
interface PatternSelectView : MvpView {

    fun showPatterns(list: List<PatternViewData>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showPatternDeleted(name: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showPatternSelected(name: String)
}