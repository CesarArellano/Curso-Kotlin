package com.cesararellano.hypnosisapp

import android.content.Context
import android.graphics.*
import android.graphics.fonts.FontFamily
import android.util.AttributeSet
import android.util.Log
import android.util.SizeF
import android.view.MotionEvent
import android.view.View
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

private const val TAG = "HypnosisView"
class HypnosisView( context: Context, attrs: AttributeSet? = null ): View(context, attrs) {
    private lateinit var tapCoors: PointF

    private val inlinePaint = Paint().apply {
        color = Color.RED
        strokeWidth = 10F
    }
    private val circlePaint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 20F
        style = Paint.Style.STROKE

    }

    private fun drawCrossOut(canvas: Canvas) {
        canvas.drawLine(0F, 0F, width.toFloat(), height.toFloat(), inlinePaint)
        canvas.drawLine( width.toFloat(), 0F, 0F, height.toFloat(), inlinePaint)
    }

    private fun drawCircle(canvas: Canvas) {
        val viewSize = SizeF( measuredWidth.toFloat(), measuredHeight.toFloat() )
        val maxRadius = max(viewSize.width, viewSize.height)
        for( currentRadius in maxRadius.toInt() downTo 0 step 70 ) {
            canvas.drawCircle(viewSize.width / 2, viewSize.height /2, currentRadius.toFloat(), circlePaint)
        }
    }

    private fun drawText(canvas: Canvas) {
        val text = "¿Tienes mucho sueño?"
        val textRectangle = Rect()
        textPaint.getTextBounds(text, 0, text.length, textRectangle)
        canvas.drawText(text, ( width - textRectangle.width()) / 2F, height / 2F, textPaint )
    }

    private val textPaint = Paint().apply {
        color = Color.BLACK
        textSize = 100F
        typeface = Typeface.create( Typeface.SANS_SERIF, Typeface.BOLD)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //drawCrossOut(canvas)
        drawCircle(canvas)
        drawText(canvas)
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        val tapPoint = PointF(event.x, event.y)
        var action = ""
        action = when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                inlinePaint.color = Color.argb(255, Random.nextInt(255), Random.nextInt(255), Random.nextInt(255))
                circlePaint.color = Color.argb(255, Random.nextInt(255), Random.nextInt(255), Random.nextInt(255))
                tapCoors = tapPoint
                invalidate()
                "ACTION_DOWN"
            }
            MotionEvent.ACTION_MOVE -> {
                this.x += tapPoint.x
                this.y += tapPoint.y
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