package com.woongi.detail.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.woongi.detail.model.PathProperties

class CanvasView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paths = mutableListOf<PathProperties>()
    private val paint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
    }
    private var backgroundBitmap: Bitmap? = null

    fun setImageBitmap(bitmap: Bitmap) {
        backgroundBitmap = bitmap
        invalidate()
    }

    fun setPath(newPaths: MutableList<PathProperties>) {
        paths.clear()
        paths.addAll(newPaths)
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        backgroundBitmap?.let { bitmap ->
            // 캔버스 크기에 맞춰서 이미지 크기 조절
            val scaled = Bitmap.createScaledBitmap(bitmap, width, height, false)
            canvas.drawBitmap(scaled, 0f, 0f, null)
        }

        for ((path, thickness, color) in paths) {
            paint.strokeWidth = thickness
            paint.color = color
            canvas.drawPath(path, paint)
        }
    }
}