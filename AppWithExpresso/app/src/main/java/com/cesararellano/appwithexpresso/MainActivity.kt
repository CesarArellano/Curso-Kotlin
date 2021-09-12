package com.cesararellano.appwithexpresso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cesararellano.appwithexpresso.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.registerButton.setOnClickListener {
            val intentDatos = Intent(this, DataActivity::class.java )
            intentDatos.apply {
                putExtra("NAME", binding.nombreEditText.text.toString() )
            }
            startActivity( intentDatos )
        }
    }
}