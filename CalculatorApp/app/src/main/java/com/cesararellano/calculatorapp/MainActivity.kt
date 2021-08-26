package com.cesararellano.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.util.Log

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
        var number = (boton as Button).text.toString()
        if( number == "𝛑") number = "3.1416"
        display.text = modeloCalculadora.agregarNumero(number)
    }

    @Suppress( "UNUSED_PARAMETER")
    fun agregarPuntoDecimalCtrl(boton: View) {
        display.text = modeloCalculadora.agregarPuntoDecimal()
    }

    @Suppress( "UNUSED_PARAMETER")
    fun cambiarSignoCtrl(boton: View) {
        display.text = modeloCalculadora.cambiarSigno()
    }

    @Suppress( "UNUSED_PARAMETER")
    fun eliminarUltimaEntradaCtrl(boton: View) {
        display.text = modeloCalculadora.eliminarUltimaEntrada()
    }

    @Suppress( "UNUSED_PARAMETER")
    fun resetearCtrl(boton: View) {
        modeloCalculadora.resetear()
        display.text = "0"
    }

    fun operadorSeleccionadoCtrl(boton: View) {
        val operador = (boton as Button).text.toString()
        if (!operador.matches( Regex("[+-/*]")) && operador != "xⁿ" && operador != "ⁿ√") {
            Log.d(TAG, "if")
            modeloCalculadora.operadorSeleccionado(operador)
            display.text = modeloCalculadora.calcularResultado()
        } else {
            Log.d(TAG, "else")
            display.text = modeloCalculadora.operadorSeleccionado(operador)
        }
    }

    @Suppress("UNUSED_PARAMETER")
    fun calcularResultadoCtrl(boton: View) {
        display.text = modeloCalculadora.calcularResultado()
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
        }

    }


}