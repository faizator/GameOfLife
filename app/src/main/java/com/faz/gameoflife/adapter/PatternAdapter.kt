package com.faz.gameoflife.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.faz.gameoflife.R
import com.faz.gameoflife.mvp.viewdata.PatternViewData

class PatternAdapter(
    private val clickListener: (patternViewData: PatternViewData) -> Unit,
    private val longClickListener: (patternViewData: PatternViewData) -> Unit
) : ListAdapter<PatternViewData, PatternAdapter.PatternViewHolder>(PatternViewDataDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PatternViewHolder(parent)

    override fun onBindViewHolder(holder: PatternViewHolder, position: Int) = holder.bind(getItem(position))

    inner class PatternViewHolder(parent: ViewGroup)
        : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.pattern_item, parent, false)) {

        private val nameView by lazy { itemView.findViewById<TextView>(R.id.nameView) }

        fun bind(item: PatternViewData) {
            nameView.text = item.name
            itemView.setOnClickListener {
                clickListener(item)
            }

            itemView.setOnLongClickListener { v ->
                longClickListener(item)
                true
            }
        }
    }

    class PatternViewDataDiffCallback : DiffUtil.ItemCallback<PatternViewData>() {
        override fun areItemsTheSame(oldItem: PatternViewData, newItem: PatternViewData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PatternViewData, newItem: PatternViewData): Boolean {
            return oldItem == newItem
        }
    }
}