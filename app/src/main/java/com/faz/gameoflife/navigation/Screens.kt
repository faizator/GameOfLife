package com.faz.gameoflife.navigation

import android.support.v4.app.Fragment
import com.faz.gameoflife.fragment.GridFragment
import com.faz.gameoflife.fragment.PatternSelectFragment
import com.faz.gameoflife.fragment.SettingsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class GridScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = GridFragment.newInstance()
    }

    class SettingsScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = SettingsFragment.newInstance()
    }

    class PatternSelectScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = PatternSelectFragment.newInstance()
    }
}