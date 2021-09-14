package com.cesararellano.fragmentsexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), ModificadorFragment.OnModifiedTextListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onPressedButton(text: String, textSize: Int) {
        println("$text hola")
    }
}