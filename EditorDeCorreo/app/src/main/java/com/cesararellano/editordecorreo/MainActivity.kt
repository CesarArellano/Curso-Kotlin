package com.cesararellano.editordecorreo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @Suppress( "UNUSED_PARAMETER")
    fun editEmail(botton: View) {
        val campoDeTexto = findViewById<TextView>( R.id.labelEmail )
        val correoActual = campoDeTexto.text.toString()
        val intent = Intent(this, NuevoCorreoActivity::class.java).apply {
            putExtra("CORREO_ACTUAL", correoActual)
        }
        startActivity( intent )
    }
}