package com.woongi.detail.ui

import android.content.Context
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

=    private val paths = mutableListOf<PathProperties>()
    private val paint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
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
        for ((path, thickness, color) in paths) {
            paint.strokeWidth = thickness
            paint.color = color
            canvas.drawPath(path, paint)
        }
    }
}