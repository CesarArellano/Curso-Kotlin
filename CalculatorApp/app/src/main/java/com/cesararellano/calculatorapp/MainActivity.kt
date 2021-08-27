package com.cesararellano.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.util.Log
import android.view.Menu
import android.widget.Toast

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private lateinit var display2: TextView
    private lateinit var display3: TextView
    private val modeloCalculadora = ModeloCalculadora()

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_main )

        display  = findViewById( R.id.display )
        display2 = findViewById( R.id.display2 )
        display3 = findViewById( R.id.display3 )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return true
    }

    @Suppress( "UNUSED_PARAMETER")
    fun agregarNumeroCtrl(boton: View) {
        val number = (boton as Button).text.toString()

        val resultados:Array<String> = modeloCalculadora.agregarNumero(number)
        if(resultados[1] == "true") {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT ).show()
        }

        display.text = resultados[0]
        cambiandoDisplay2()

    }

    @Suppress( "UNUSED_PARAMETER")
    fun agregarPuntoDecimalCtrl(boton: View) {
        display.text = modeloCalculadora.agregarPuntoDecimal()
        cambiandoDisplay2()
    }

    @Suppress( "UNUSED_PARAMETER")
    fun cambiarSignoCtrl(boton: View) {
        display.text = modeloCalculadora.cambiarSigno()
        cambiandoDisplay2()
    }

    @Suppress( "UNUSED_PARAMETER")
    fun eliminarUltimaEntradaCtrl(boton: View) {
        display.text = modeloCalculadora.eliminarUltimaEntrada()
        cambiandoDisplay2()
    }

    @Suppress( "UNUSED_PARAMETER")
    fun resetearCtrl(boton: View) {
        modeloCalculadora.resetear()
        display.text = "0"
        display2.text = "0"
    }

    fun operadorSeleccionadoCtrl(boton: View) {
        val operador = (boton as Button).text.toString()
        val operacionActual = display2.text.toString()
        val resultadosOperador:Array<String>
        val resuladosCalcular:Array<String>

        if (!operador.matches( Regex("[+-/*]")) && operador != "xⁿ" && operador != "ⁿ√") {
            Log.d(TAG, "sin cos √ 10ⁿ 1/X")
            modeloCalculadora.operadorSeleccionado(operador, operacionActual, true)
            resuladosCalcular = modeloCalculadora.calcularResultado()
            if( resuladosCalcular[1] == "true") showToast()
            display.text = resuladosCalcular[0]
            display2.text = "0"
        } else {
            Log.d(TAG, "+ - / * xⁿ ⁿ√")
            resultadosOperador = modeloCalculadora.operadorSeleccionado(operador, operacionActual, false)
            display.text = resultadosOperador[0]
            display2.text = resultadosOperador[1]
        }
    }

    @Suppress("UNUSED_PARAMETER")
    fun calcularResultadoCtrl(boton: View) {
        val resultados:Array<String> = modeloCalculadora.calcularResultado()
        display.text = resultados[0]
        display2.text = "0"
        if( resultados[1] == "true") showToast()
    }

    @Suppress( "UNUSED_PARAMETER")
    fun operacionEnMemoriaCtrl(boton: View) {
        val operacion = (boton as Button).text.toString()
        val opcionRecall = "Recall"

        if( operacion == opcionRecall) {
            display.text = modeloCalculadora.operacionEnMemoria(opcionRecall)
            cambiandoDisplay2()
        } else {
            val numeroEnDisplay = display.text.toString()
            modeloCalculadora.operacionEnMemoria(operacion, numeroEnDisplay)
        }

        val operandoEnMemoria = modeloCalculadora.getOperandoEnMemoria()
        display3.text = ("Mem: $operandoEnMemoria")

    }

    private fun cambiandoDisplay2() {
        val operacionActual = display2.text.split(" ")
        if (operacionActual.size > 1)  {
            display2.text = ("${operacionActual[0]} ${operacionActual[1]} ${display.text}")
        } else {
            display2.text = display.text
        }
    }

    private fun showToast() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT ).show()
    }

    @Suppress("UNUSED_PARAMETER")
    fun cambiarGradosRadianes(item: android.view.MenuItem) {
        if( modeloCalculadora.getEstado() ) {
            supportActionBar!!.title = "Calculator App en Deg"
        } else {
            supportActionBar!!.title = "Calculator App en Rads"
        }
        modeloCalculadora.setEstado()
    }

}