package com.cesararellano.editordecorreo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class NuevoCorreoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_correo)
        val campoDeEdicion = findViewById<EditText>( R.id.editTextTextEmailAddress)
        campoDeEdicion.setText( intent.getStringExtra("CORREO_ACTUAL"))
    }
}