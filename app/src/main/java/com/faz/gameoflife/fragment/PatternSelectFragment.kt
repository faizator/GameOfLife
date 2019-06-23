package com.faz.gameoflife.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView.VERTICAL
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.faz.gameoflife.R
import com.faz.gameoflife.adapter.PatternAdapter
import com.faz.gameoflife.common.kextension.appComponent
import com.faz.gameoflife.mvp.presenter.PatternSelectPresenter
import com.faz.gameoflife.mvp.view.PatternSelectView
import com.faz.gameoflife.mvp.viewdata.PatternViewData
import kotlinx.android.synthetic.main.pattern_select_fragment.*

class PatternSelectFragment : MvpAppCompatFragment(), PatternSelectView {

    @InjectPresenter
    lateinit var presenter: PatternSelectPresenter

    @ProvidePresenter
    fun providePresenter() = appComponent.patternSelectPresenter

    private val patternAdapter by lazy {
        PatternAdapter({
            presenter.selectPattern(it)
        }, {
            presenter.deletePattern(it)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.pattern_select_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.title = "Выбор шаблона"
        patternsList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, VERTICAL, false)
            adapter = patternAdapter
        }
    }

    override fun showPatterns(list: List<PatternViewData>) {
        patternAdapter.submitList(list)
    }

    override fun showPatternDeleted(name: String) {
        Toast.makeText(context, "Шаблон $name удалён", Toast.LENGTH_SHORT).show()
    }

    override fun showPatternSelected(name: String) {
        Toast.makeText(context, "Выбран шаблон $name", Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = PatternSelectFragment()
    }
}