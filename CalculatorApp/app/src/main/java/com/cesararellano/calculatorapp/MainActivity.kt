package com.cesararellano.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
// import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

//private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var display: TextView
    private var usuarioEstaEscribiendoNumero = false
    private var operandoEnEspera = 0 // Primer operando
    private var secondOperando = 0
    private var operacionEnEsperaDeOperando = ""
    private var resultado = 0
    /*
    private lateinit var incrementButton: Button
    private lateinit var resetButton: Button

    private var counter: Int = 0
    */
    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_main )

        display = findViewById( R.id.display)
        /*
        display = findViewById( R.id.display)
        incrementButton = findViewById( R.id.firstButton )
        resetButton = findViewById( R.id.resetButton )

        incrementButton.setOnClickListener {
            Log.d(TAG, "Pressing accept")
            counter++
            display.text = counter.toString()
        }
        resetButton.setOnClickListener {
            counter = 0
            display.text = counter.toString()
        }
        */
    }

    @Suppress( "UNUSED_PARAMETER")
    fun onPressedButton(button: View) {
        val digito = (button as Button).text
        if(usuarioEstaEscribiendoNumero) {
            display.append(digito)
        } else {
            display.text = digito
            usuarioEstaEscribiendoNumero = true
        }

    }

    fun operacionPresionado(botton: View) {
        val operacionPresionada = (botton as Button).text.toString()
        if( usuarioEstaEscribiendoNumero ) {
            secondOperando = display.text.toString().toInt()
            usuarioEstaEscribiendoNumero = false
        }
        resultado = ejecutaOperacion(operacionPresionada)
        display.text = resultado.toString()
    }

    private fun ejecutaOperacion(operacion: String): Int {
        ejecutaOperacionEnEspera()
        operacionEnEsperaDeOperando = operacion
        operandoEnEspera = secondOperando
        return secondOperando
    }

    private fun ejecutaOperacionEnEspera() {
        when(operacionEnEsperaDeOperando) {
            "+" -> secondOperando += operandoEnEspera
            "-" -> secondOperando = operandoEnEspera - secondOperando
            "*" -> secondOperando *= operandoEnEspera
            "/" -> {
                if (secondOperando != 0) {
                    secondOperando = operandoEnEspera / secondOperando
                }
            }
        }
    }

}