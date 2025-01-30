package com.woongi.detail.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.woongi.domain.point.entity.Canvas

class DetailDiffUtil(
    private val oldList: List<Canvas>,
    private val newList: List<Canvas>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}