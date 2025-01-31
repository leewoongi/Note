package com.woongi.detail.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.woongi.detail.R
import com.woongi.domain.point.entity.Canvas
import com.woongi.domain.point.entity.Path

class DetailRecyclerViewAdapter(
) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    private val items = mutableListOf<Path>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recyclerview, parent, false)
        return DetailViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        if(holder is DetailViewHolder){
            holder.bind(
                path = items[position],
                onClick = { }
            )
        }
    }

    fun set(newItems: List<Path>) {
        val diffCallback = DetailDiffUtil(items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    fun remove(position: Int) {

    }
}
