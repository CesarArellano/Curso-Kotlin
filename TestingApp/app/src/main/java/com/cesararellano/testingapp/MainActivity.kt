package com.cesararellano.testingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cesararellano.testingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.registerButton.setOnClickListener {
            val dataIntent = Intent(this, DataActivity::class.java)
            dataIntent.apply {
                putExtra("NAME", binding.nameEditText.text.toString() )
            }
            startActivity(dataIntent)
        }
    }
}