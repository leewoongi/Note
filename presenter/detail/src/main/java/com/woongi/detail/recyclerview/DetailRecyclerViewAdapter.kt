package com.woongi.detail.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.woongi.detail.R
import com.woongi.domain.point.entity.Line
import com.woongi.domain.point.entity.Path

class DetailRecyclerViewAdapter(
    private val onClick: (Path) -> Unit,
    private val onRemove: (Path) -> Unit
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
                onClick = { onClick(items[position]) }
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
        val removedItem = items[position]

        val newItems = items.toMutableList()
        newItems.removeAt(position)

        val diffCallback = DetailDiffUtil(items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
        onRemove(removedItem)
    }
}
