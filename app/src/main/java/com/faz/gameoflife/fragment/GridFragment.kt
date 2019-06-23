package com.faz.gameoflife.fragment

import android.os.Bundle
import android.view.*
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.faz.gameoflife.common.kextension.appComponent
import com.faz.gameoflife.mvp.presenter.GridPresenter
import com.faz.gameoflife.mvp.view.GridView
import com.faz.gameoflife.mvp.viewdata.GridViewData
import kotlinx.android.synthetic.main.grid_fragment.*
import android.text.InputType
import android.widget.EditText
import android.app.AlertDialog
import android.widget.Toast
import com.faz.gameoflife.R


class GridFragment : MvpAppCompatFragment(), GridView {

    @InjectPresenter
    lateinit var presenter: GridPresenter

    @ProvidePresenter
    fun providePresenter() = appComponent.gridPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.grid_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.title = "GameOfLife"
        playBtn.setOnClickListener { presenter.togglePlay() }
        stepBtn.setOnClickListener { presenter.step() }
        saveBtn.setOnClickListener { showSaveDialog() }
        reloadBtn.setOnClickListener { presenter.reloadSelected() }
        clearBtn.setOnClickListener { presenter.clear() }
        gridWidget.cellClickListener = { row, col ->
            presenter.toggleCell(row, col)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.grid_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.settingsMenu -> {
            presenter.openSettings()
            true
        }
        R.id.patternSelectMenu -> {
            presenter.openPatternSelect()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun setData(data: GridViewData) {
        gridWidget.data = data.data
    }

    override fun onPlayState() {
        playBtn.setImageResource(R.drawable.ic_pause)
    }

    override fun onStopState() {
        playBtn.setImageResource(R.drawable.ic_play)
    }

    override fun showPatternSaved(name: String) {
        Toast.makeText(context, "Шаблон $name сохранён", Toast.LENGTH_SHORT).show()
    }

    private fun showSaveDialog() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Название шаблона")
        val input = EditText(context)
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)
        builder.setPositiveButton("Сохранить") { _, _ -> presenter.save(input.text.toString()) }
        builder.setNegativeButton("Отмена") { dialog, _ -> dialog.cancel() }
        builder.show()
    }

    companion object {
        fun newInstance() = GridFragment()
    }
}