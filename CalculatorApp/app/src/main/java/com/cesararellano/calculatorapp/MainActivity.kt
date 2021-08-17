package com.cesararellano.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var firstButton: Button
    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_main )
        firstButton = findViewById( R.id.firstButton )
        firstButton.setOnClickListener {
            Log.d(TAG, "Pressing mi firstButton")
        }
    }
}