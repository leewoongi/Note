package com.woongi.detail.recyclerview

import android.graphics.Path
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.woongi.core.extension.dpToPx
import com.woongi.detail.R
import com.woongi.detail.ui.CanvasView
import com.woongi.domain.point.entity.Point
import com.woongi.domain.point.entity.constants.PathType

internal class DetailViewHolder(
    private val view: View
) : ViewHolder(view) {
    private lateinit var canvasView: CanvasView
    private lateinit var textView: TextView

    fun bind(
        path: com.woongi.domain.point.entity.Path,
        onClick: () -> Unit
    ) {
        canvasView = view.findViewById(R.id.canvas)
        textView = view.findViewById(R.id.tv_description)

        // 전체화면에 그려진 선들을 54dp 사이즈 캔버스에 들어가게 하기위해서 스케일링

        val minX = path.path.flatMap { it.points }.minOf { it.pointX }
        val maxX = path.path.flatMap { it.points }.maxOf { it.pointX }
        val minY = path.path.flatMap { it.points }.minOf { it.pointY }
        val maxY = path.path.flatMap { it.points }.maxOf { it.pointY }

        val targetSize = 54.dpToPx(context = view.context)
        val scaleX = targetSize / (maxX - minX)
        val scaleY = targetSize / (maxY - minY)


        val pair: MutableList<Pair<Path, Float>> = mutableListOf()
        path.path.forEach { line ->
            val paths = Path()
            line.points.forEach { point: Point ->
                when (point.type) {
                    PathType.MOVE_TO -> {
                        paths.moveTo(
                            (point.pointX - minX) * scaleX,
                            (point.pointY - minY) * scaleY
                        )
                    }

                    PathType.LINE_TO -> {
                        paths.lineTo(
                            (point.pointX - minX) * scaleX,
                            (point.pointY - minY) * scaleY
                        )
                    }
                }
            }
            pair.add(Pair(paths, line.thickness))
        }

        canvasView.setPath(pair)
        textView.text = "TEST TEST TEST"
        view.rootView.setOnClickListener {
            onClick()
        }
    }
}
