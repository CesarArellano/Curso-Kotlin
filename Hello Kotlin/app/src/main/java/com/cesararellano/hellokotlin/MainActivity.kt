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
        var message: String = binding.editMessage.text.toString()
        if(message.isEmpty()) {
            binding.txtStatus.text = "Introduzca un texto"
            message = "¿Es en serio? Ya pon algo en el Edit Text!"
        }
        tts!!.speak(message, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            binding.txtStatus.text = "Listo"
            tts!!.language = Locale("ES")
        } else {
            binding.txtStatus.text = "No disponible!"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
    }
}