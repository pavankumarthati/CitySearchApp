package com.masterbit.populationsample

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class PopulationItemAdapter(): ListAdapter<PopulationItemData, PopulationItemAdapter.PopulationItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopulationItemViewHolder {
        return PopulationItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.population_item, parent, false))
    }

    override fun onBindViewHolder(holder: PopulationItemViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    class PopulationItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val rowTv: TextView = view.findViewById(R.id.row)

        fun bind(data: PopulationItemData) {
            rowTv.text = data.name
            rowTv.setTypeface(null, if (data.isCountry) Typeface.BOLD else Typeface.NORMAL)
        }
    }
}

class DiffCallback: DiffUtil.ItemCallback<PopulationItemData>() {

    override fun areItemsTheSame(
        oldItem: PopulationItemData,
        newItem: PopulationItemData
    ): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: PopulationItemData,
        newItem: PopulationItemData
    ): Boolean {
        return oldItem == newItem
    }

}