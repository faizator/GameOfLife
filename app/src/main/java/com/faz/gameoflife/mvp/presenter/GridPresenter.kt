package com.faz.gameoflife.mvp.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.faz.gameoflife.common.kextension.addTo
import com.faz.gameoflife.interactor.GridInteractor
import com.faz.gameoflife.mvp.view.GridView
import com.faz.gameoflife.mvp.viewdata.GridViewData
import com.faz.gameoflife.navigation.MainRouter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposables
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@InjectViewState
class GridPresenter @Inject constructor(
    private val router: MainRouter,
    private val interactor: GridInteractor
) : MvpPresenter<GridView>() {

    private val compositeDisposable = CompositeDisposable()

    private var isPlaying = false

    private var playSubscription = Disposables.disposed()

    override fun onFirstViewAttach() {
        reloadSelected()
    }

    override fun onDestroy() = compositeDisposable.dispose()

    fun togglePlay() {
        isPlaying = !isPlaying
        if (isPlaying) {
            playSubscription = Observable.interval(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { step() }
                .addTo(compositeDisposable)
            viewState.onPlayState()
        } else {
            playSubscription.dispose()
            viewState.onStopState()
        }
    }

    fun step() {
        interactor.step()
        updateViewData()
    }

    fun toggleCell(row: Int, col: Int) {
        interactor.toggleCell(row, col)
        updateViewData()
    }

    fun save(name: String) {
        interactor.save(name)
            .subscribe({
                viewState.showPatternSaved(name)
            }, {})
            .addTo(compositeDisposable)
    }

    fun reloadSelected() {
        interactor.reloadSelected()
            .subscribe({
                updateViewData()
            }, {})
            .addTo(compositeDisposable)
    }

    fun clear() {
        interactor.clear()
        updateViewData()
    }

    fun openSettings() = router.openSettingsScreen()

    fun openPatternSelect() = router.openPatternSelectScreen()

    private fun updateViewData() {
        viewState.setData(GridViewData(interactor.data))
    }
}