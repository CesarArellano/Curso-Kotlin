package com.cesararellano.hypnosisapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.core.view.GestureDetectorCompat

private const val TAG = "HypnosisActivity"
private lateinit var hypnosisView: HypnosisView

class HypnosisActivity : AppCompatActivity() {
    private lateinit var gestureDetector: GestureDetectorCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hypnosis)
        // val hypnosis = HypnosisView(this)
        supportActionBar?.hide()
        hypnosisView = findViewById(R.id.hypnosisView)
        // gestureDetector = GestureDetectorCompat(this, this)
        gestureDetector = GestureDetectorCompat(this, GestosListener())
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        /*
        val position = PointF(event.x, event.y)


        val action = when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                "ACTION_DOWN"
            }
            MotionEvent.ACTION_MOVE -> {
                hypnosisView.x = position.x
                hypnosisView.y = position.y
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
        Log.d(TAG, "La acción $action fue en x = ${ position.x }, y = ${ position.y }")
        */
        if ( gestureDetector.onTouchEvent(event) ) {
            return true
        }
        super.onTouchEvent(event)
        return false
    }

    private class GestosListener : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent?): Boolean {
            Log.d(TAG, "OnDown en el Listener anidado ")
            return true
        }
        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            Log.d(TAG, "onScroll $e1 $e2 $distanceX $distanceY")
            return true
        }
    }

}