package com.faz.gameoflife.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.faz.gameoflife.R
import com.faz.gameoflife.common.kextension.appComponent
import com.faz.gameoflife.mvp.presenter.SettingsPresenter
import com.faz.gameoflife.mvp.view.SettingsView
import kotlinx.android.synthetic.main.settings_fragment.*

class SettingsFragment : MvpAppCompatFragment(), SettingsView {

    @InjectPresenter
    lateinit var presenter: SettingsPresenter

    @ProvidePresenter
    fun providePresenter() = appComponent.settingsPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.settings_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.title = "Настройки"

        gridWidthSeek.setOnSeekBarChangeListener(
            SeekBarProgressListener { presenter.setWidthAsPercent(it) }
        )

        gridHeightSeek.setOnSeekBarChangeListener(
            SeekBarProgressListener { presenter.setHeightAsPercent(it) }
        )
    }

    override fun updateControls(widthAsPercent: Int, heightAsPercent: Int) {
        gridWidthSeek.progress = widthAsPercent
        gridHeightSeek.progress = heightAsPercent
    }

    override fun showGridWidth(value: Int) {
        gridWidthView.text = "$value"
    }

    override fun showGridHeight(value: Int) {
        gridHeightView.text = "$value"
    }

    class SeekBarProgressListener(
        private val progressListenerDelegate: (progress: Int) -> Unit
    ) : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            if (!fromUser) return
            progressListenerDelegate(progress)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {
            // ignored
        }

        override fun onStopTrackingTouch(seekBar: SeekBar) {
            // ignored
        }
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }
}