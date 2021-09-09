package com.cesararellano.editordecorreo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private val awaitResponse = registerForActivityResult( ActivityResultContracts.StartActivityForResult() ) {
            resp ->
        if( resp.resultCode == Activity.RESULT_OK ) {
            val datos: Intent? = resp.data
            findViewById<TextView>(R.id.labelEmail).text = datos?.getStringExtra("NEW_EMAIL")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @Suppress( "UNUSED_PARAMETER")
    fun editEmail(botton: View) {
        val labelEmail = findViewById<TextView>( R.id.labelEmail )
        val currentEmail = labelEmail.text.toString()
        //val intent = Intent(this, NuevoCorreoActivity::class.java).apply {
        //    putExtra("CORREO_ACTUAL", correoActual)
        //}
        val intent = NuevoCorreoActivity.nuevaInstancia(this, currentEmail)
        awaitResponse.launch(intent)
        //startActivity( intent )
    }
}