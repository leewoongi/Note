package com.woongi.detail.recyclerview

import android.graphics.Path
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.woongi.detail.R
import com.woongi.detail.ui.CanvasView
import com.woongi.domain.point.entity.Canvas

internal class DetailViewHolder(
    private val view: View
) : ViewHolder(view) {
    private lateinit var canvasView: CanvasView
    private lateinit var textView: TextView

    fun bind(
        canvas: Canvas,
        onClick: () -> Unit
    ){
        canvasView = view.findViewById(R.id.canvas)
        textView = view.findViewById(R.id.tv_description)

        val path = Path().apply {
            // canvas로부터 경로 정보 추출해서 설정
            // 예시로 canvas의 ID를 사용했다고 가정
            moveTo(0f, 0f) // 시작 점
            lineTo(100f, 100f) // 끝 점
        }

        canvasView.setPath(path)
        textView.text = "TEST TEST TEST"
        view.rootView.setOnClickListener {
            onClick()
        }
    }
}