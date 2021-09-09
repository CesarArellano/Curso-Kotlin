package com.cesararellano.editordecorreo

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class NuevoCorreoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_correo)
        val emailEditText = findViewById<EditText>( R.id.emailEditText)
        emailEditText.setText( intent.getStringExtra("CURRENT_EMAIL"))
    }

    fun ok( @Suppress("UNUSED_PARAMETER") botton: View ) {
        val newEmail = findViewById<EditText>(R.id.emailEditText).text.toString()

        if( newEmail.isEmpty() ) {
            Toast.makeText(this, "El campo no puede quedar vacío", Toast.LENGTH_SHORT ).show()
            return
        }

        val data = Intent().apply {
            putExtra("NEW_EMAIL", newEmail)
        }
        setResult(Activity.RESULT_OK, data)
        finish()
    }

    fun cancel( @Suppress("UNUSED_PARAMETER") botton: View ) {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    companion object {
        fun nuevaInstancia(context: Context, currentEmail: String ): Intent {
            return Intent(context, NuevoCorreoActivity::class.java).apply{
                putExtra("CURRENT_EMAIL", currentEmail)
            }
        }
    }
}