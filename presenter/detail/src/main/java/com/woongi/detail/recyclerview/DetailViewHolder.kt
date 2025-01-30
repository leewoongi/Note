package com.woongi.detail.recyclerview

import android.graphics.Path
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.woongi.core.extension.dpToPx
import com.woongi.detail.R
import com.woongi.detail.ui.CanvasView
import com.woongi.domain.point.entity.constants.PathType

internal class DetailViewHolder(
    private val view: View
) : ViewHolder(view) {
    private lateinit var canvasView: CanvasView
    private lateinit var textView: TextView

    fun bind(
        path: com.woongi.domain.point.entity.Path,
        onClick: () -> Unit
    ){
        canvasView = view.findViewById(R.id.canvas)
        textView = view.findViewById(R.id.tv_description)

        // 전체화면에 그려진 선들을 54dp 사이즈 캔버스에 들어가게 하기위해서 스케일링
        val minX = path.path.minOf { it.pointX }
        val maxX = path.path.maxOf { it.pointX }
        val minY = path.path.minOf { it.pointY }
        val maxY = path.path.maxOf { it.pointY }

        val targetSize = 54.dpToPx(context = view.context)
        val scaleX = targetSize / (maxX - minX)
        val scaleY = targetSize / (maxY - minY)

        val paths = Path()
        path.path.forEach {
            when(it.type){
                PathType.MOVE_TO -> {
                    paths.moveTo(
                        (it.pointX - minX) * scaleX,
                        (it.pointY - minY) * scaleY
                    )
                }
                PathType.LINE_TO -> {
                    paths.lineTo(
                        (it.pointX - minX) * scaleX,
                        (it.pointY - minY) * scaleY
                    )
                }
            }
        }

        canvasView.setPath(paths)
        textView.text = "TEST TEST TEST"
        view.rootView.setOnClickListener {
            onClick()
        }
    }
}