package com.faz.gameoflife.activity

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.faz.gameoflife.R
import com.faz.gameoflife.common.kextension.appComponent
import com.faz.gameoflife.common.kextension.inject
import com.faz.gameoflife.mvp.presenter.MainPresenter
import com.faz.gameoflife.mvp.view.MainView
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : MvpAppCompatActivity(), MainView {

    private val navigatorHolder by inject { mainNavigationHolder }
    private val navigator by lazy { SupportAppNavigator(this, R.id.container) }

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter() = appComponent.mainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            presenter.openGridScreen()
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount < 2) {
            presenter.finish()
        } else {
            presenter.back()
        }
    }
}
