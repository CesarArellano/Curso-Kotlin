package com.cesararellano.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.util.Log
import android.widget.Toast

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private lateinit var display2: TextView
    private val modeloCalculadora = ModeloCalculadora()

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_main )

        display = findViewById( R.id.display )
        display2 = findViewById( R.id.display2 )
    }

    @Suppress( "UNUSED_PARAMETER")
    fun agregarNumeroCtrl(boton: View) {
        val number = (boton as Button).text.toString()

        val resultados:Array<String> = modeloCalculadora.agregarNumero(number)
        if(resultados[1] == "true") {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT ).show()
        }

        display.text = resultados[0]
        changeDisplay2()

    }

    private fun changeDisplay2() {
        val operacionActual = display2.text.split(" ")
        if (operacionActual.size > 1)  {
            display2.text = ("${operacionActual[0]} ${operacionActual[1]} ${display.text}")
        } else {
            display2.text = display.text
        }
    }

    @Suppress( "UNUSED_PARAMETER")
    fun agregarPuntoDecimalCtrl(boton: View) {
        display.text = modeloCalculadora.agregarPuntoDecimal()
        changeDisplay2()
    }

    @Suppress( "UNUSED_PARAMETER")
    fun cambiarSignoCtrl(boton: View) {
        display.text = modeloCalculadora.cambiarSigno()
        changeDisplay2()
    }

    @Suppress( "UNUSED_PARAMETER")
    fun eliminarUltimaEntradaCtrl(boton: View) {
        display.text = modeloCalculadora.eliminarUltimaEntrada()
        changeDisplay2()
    }

    @Suppress( "UNUSED_PARAMETER")
    fun resetearCtrl(boton: View) {
        modeloCalculadora.resetear()
        display.text = "0"
        display2.text = ""
    }

    fun operadorSeleccionadoCtrl(boton: View) {
        val operador = (boton as Button).text.toString()
        val operacionActual = display2.text.toString()
        val resultadosOperador:Array<String>
        val resuladosCalcular:Array<String>

        if (!operador.matches( Regex("[+-/*]")) && operador != "xⁿ" && operador != "ⁿ√") {
            Log.d(TAG, "if")
            modeloCalculadora.operadorSeleccionado(operador, operacionActual, true)
            resuladosCalcular = modeloCalculadora.calcularResultado()
            if( resuladosCalcular[1] == "true") showToast()
            display.text = resuladosCalcular[0]
            display2.text = ""
        } else {
            Log.d(TAG, "else")
            resultadosOperador = modeloCalculadora.operadorSeleccionado(operador, operacionActual, false)
            display.text = resultadosOperador[0]
            display2.text = resultadosOperador[1]
        }
    }

    @Suppress("UNUSED_PARAMETER")
    fun calcularResultadoCtrl(boton: View) {
        val resultados:Array<String> = modeloCalculadora.calcularResultado()
        display.text = resultados[0]
        display2.text = ""
        if( resultados[1] == "true") showToast()
    }

    @Suppress( "UNUSED_PARAMETER")
    fun operacionEnMemoriaCtrl(boton: View) {
        val operacion = (boton as Button).text.toString()
        val opcionRecall = "Recall"

        if( operacion == opcionRecall) {
            display.text = modeloCalculadora.operacionEnMemoria(opcionRecall)
        } else {
            val numeroEnDisplay = display.text.toString()
            modeloCalculadora.operacionEnMemoria(operacion, numeroEnDisplay)
            if(operacion == "MC") {
                display2.text = ""
            }
        }

    }

    private fun showToast() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT ).show()
    }

}