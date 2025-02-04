package com.woongi.detail.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class CanvasView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    // path와 현재는 thickness 정보만 가지고 있음
    private val paths = mutableListOf<Pair<Path, Float>>()
    private val paint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
    }

    fun setPath(newPaths: MutableList<Pair<Path, Float>>) {
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
        for ((path, thickness) in paths) {
            paint.strokeWidth = thickness
            canvas.drawPath(path, paint)
        }
    }
}