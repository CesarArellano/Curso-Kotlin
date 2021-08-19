package com.cesararellano.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var incrementButton: Button
    private lateinit var resetButton: Button
    private lateinit var display: TextView
    private var counter: Int = 0

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_main )

        display = findViewById( R.id.display)
        incrementButton = findViewById( R.id.firstButton )
        resetButton = findViewById( R.id.resetButton )

        incrementButton.setOnClickListener {
            Log.d(TAG, "Pressing accept")
            counter++
            display.text = counter.toString()
        }
        resetButton.setOnClickListener {
            counter = 0
            display.text = counter.toString()
        }
    }

    @Suppress( "UNUSED_PARAMETER")
    fun onPressedButton(button: View) {
        Log.d(TAG, "Pressing cancel")
        if( counter > 0) {
            counter--
            display.text = counter.toString()
        }
    }
}