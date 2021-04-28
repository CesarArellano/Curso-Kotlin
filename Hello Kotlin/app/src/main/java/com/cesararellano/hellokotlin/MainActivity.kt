package com.cesararellano.hellokotlin
import kotlinx.android.synthetic.main.activity_main.*

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    var tts: TextToSpeech? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tts = TextToSpeech(this, this)
        btnPlay.setOnClickListener{speak()}
    }

    private fun speak() {
        var message: String = textView.text.toString()
        tts!!.speak(message, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            textView.text = "Hola, soy Kotlin"
            tts!!.setLanguage(Locale("ES"))
        } else {
            textView.text = "No disponible!"
        }
    }
}