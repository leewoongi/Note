package com.woongi.detail.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.woongi.domain.point.entity.Path

class DetailDiffUtil(
    private val oldList: List<Path>,
    private val newList: List<Path>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldList[oldItemPosition].path == newList[newItemPosition].path
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}