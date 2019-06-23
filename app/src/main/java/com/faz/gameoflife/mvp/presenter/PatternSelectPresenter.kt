package com.faz.gameoflife.mvp.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.faz.gameoflife.common.kextension.addTo
import com.faz.gameoflife.interactor.PatternSelectInteractor
import com.faz.gameoflife.mvp.view.PatternSelectView
import com.faz.gameoflife.mvp.viewdata.PatternViewData
import com.faz.gameoflife.navigation.MainRouter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class PatternSelectPresenter @Inject constructor(
    private val router: MainRouter,
    private val interactor: PatternSelectInteractor
) : MvpPresenter<PatternSelectView>() {

    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() = reloadPatterns()

    fun selectPattern(patternViewData: PatternViewData) {
        viewState.showPatternSelected(patternViewData.name)
        interactor.setSelectedPatternId(patternViewData.id)
        router.exit()
    }

    fun deletePattern(patternViewData: PatternViewData) {
        interactor.deletePatternById(patternViewData.id)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.showPatternDeleted(patternViewData.name)
                reloadPatterns()
            }, {})
            .addTo(compositeDisposable)
    }

    private fun reloadPatterns() {
        interactor.getAllPatterns()
            .subscribeOn(Schedulers.single())
            .observeOn(Schedulers.computation())
            .map { patterns -> patterns.map { PatternViewData(it.id, it.name) } }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.showPatterns(it)
            }, {})
            .addTo(compositeDisposable)
    }
}