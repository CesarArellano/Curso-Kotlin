package com.cesararellano.hypnosisapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class HypnosisActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hypnosis)
        // val hypnosis = HypnosisView(this)
        supportActionBar?.hide()
    }
}