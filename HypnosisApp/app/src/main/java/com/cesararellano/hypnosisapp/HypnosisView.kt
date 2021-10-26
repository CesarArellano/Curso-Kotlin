package com.cesararellano.hypnosisapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

//private const val TAG = "HypnosisView"
class HypnosisView( context: Context, attrs: AttributeSet? = null ): View(context, attrs) {
    private val paintInline = Paint().apply {
        color = Color.RED
        strokeWidth = 5F

    }

    private fun drawCrossOut(canvas: Canvas) {
        canvas.drawLine(0F, 0F, width.toFloat(), height.toFloat(), paintInline)
        canvas.drawLine( width.toFloat(), 0F, 0F, height.toFloat(), paintInline)
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCrossOut(canvas)
    }
}