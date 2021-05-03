package com.cesararellano.hellokotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import com.cesararellano.hellokotlin.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var binding: ActivityMainBinding
    var tts: TextToSpeech? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tts = TextToSpeech(this, this)
        binding.btnPlay.setOnClickListener{speak()}
    }

    private fun speak() {
        var message: String = binding.txtStatus.text.toString()
        tts!!.speak(message, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            binding.txtStatus.text = "Hola, soy Kotlin"
            tts!!.language = Locale("ES")
        } else {
            binding.txtStatus.text = "No disponible!"
        }
    }
}