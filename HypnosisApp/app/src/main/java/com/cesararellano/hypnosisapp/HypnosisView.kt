package com.cesararellano.hypnosisapp

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.math.min
import kotlin.random.Random

private const val TAG = "HypnosisView"
class HypnosisView( context: Context, attrs: AttributeSet? = null ): View(context, attrs) {
    private val inlinePaint = Paint().apply {
        color = Color.RED
        strokeWidth = 10F
    }
    private val circlePaint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 10F
        style = Paint.Style.STROKE

    }

    private fun drawCrossOut(canvas: Canvas) {
        canvas.drawLine(0F, 0F, width.toFloat(), height.toFloat(), inlinePaint)
        canvas.drawLine( width.toFloat(), 0F, 0F, height.toFloat(), inlinePaint)
    }

    private fun drawCircle(canvas: Canvas) {
        val radius = min(width, height)
        canvas.drawCircle(width.toFloat() / 2, height.toFloat() / 2, (radius / 2).toFloat() - 100F, circlePaint)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCrossOut(canvas)
        drawCircle(canvas)
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        val tapPoint = PointF(event.x, event.y)
        var action = ""
        action = when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                inlinePaint.color = Color.argb(255, Random.nextInt(255), Random.nextInt(255), Random.nextInt(255))
                circlePaint.color = Color.argb(255, Random.nextInt(255), Random.nextInt(255), Random.nextInt(255))
                invalidate()
                "ACTION_DOWN"
            }
            MotionEvent.ACTION_MOVE -> {
                "ACTION_MOVE"
            }
            MotionEvent.ACTION_UP -> {
                "ACTION_UP"
            }
            MotionEvent.ACTION_CANCEL ->  {
                "ACTION_CANCEL"
            }
            else -> "OTHER ACTION"
        }
        Log.d(TAG, "La acción $action fue en x = ${ tapPoint.x }, y = ${ tapPoint.y }")
        return true
    }
}