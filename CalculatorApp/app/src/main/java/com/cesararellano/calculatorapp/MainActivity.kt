package com.cesararellano.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.view.Menu
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private lateinit var display2: TextView
    private lateinit var display3: TextView
    private val modeloCalculadora = ModeloCalculadora()

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_main )

        supportActionBar!!.title = "Calculator App | RAD"
        display  = findViewById( R.id.display )
        display2 = findViewById( R.id.display2 )
        display3 = findViewById( R.id.display3 )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return true
    }

    fun agregarNumeroCtrl(boton: View) {
        val number = (boton as Button).text.toString()
        val resultados:Array<String> = modeloCalculadora.agregarNumero(number)

        if( resultados[1] == "true" ) showToast()

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
        display3.text = ("Mem: 0")
    }

    fun operadorSeleccionadoCtrl(boton: View) {
        val operador = (boton as Button).text.toString()
        val operacionActual = display2.text.toString()
        val resultadosOperador:Array<String>
        val resuladosCalcular:Array<String>

        if ( !operador.matches( Regex("[+-/*]") ) && operador != "xⁿ" && operador != "ⁿ√") {
            modeloCalculadora.operadorSeleccionado(operador, true)
            resuladosCalcular = modeloCalculadora.calcularResultado()

            if( resuladosCalcular[1] == "true" ) showToast()

            display.text = resuladosCalcular[0]
            display2.text = "0"
        } else {
            resultadosOperador = modeloCalculadora.operadorSeleccionado(operador, false, operacionActual)

            if( resultadosOperador[2] == "true" ) showToast()

            display.text = resultadosOperador[0]
            display2.text = resultadosOperador[1]
        }
    }

    @Suppress("UNUSED_PARAMETER")
    fun calcularResultadoCtrl(boton: View) {
        val resultados:Array<String> = modeloCalculadora.calcularResultado()

        if( resultados[1] == "true") showToast()

        display.text = resultados[0]
        display2.text = "0"
    }

    fun operacionEnMemoriaCtrl(boton: View) {
        val operacion = (boton as Button).text.toString()
        val opcionRecall = getString(R.string.botonRecall)

        if( operacion == opcionRecall ) {
            display.text = modeloCalculadora.operacionEnMemoria(opcionRecall)
            cambiandoDisplay2()
        } else {
            modeloCalculadora.operacionEnMemoria(operacion)
        }

        val operandoEnMemoria = modeloCalculadora.getOperandoEnMemoria()
        display3.text = ("Mem: $operandoEnMemoria")

    }

    fun cambiarGradosRadianes(item: android.view.MenuItem) {
        if( modeloCalculadora.getEstado() ) {
            item.title = "Cambiar a radianes"
            supportActionBar!!.title = "Calculator App | DEG"
        } else {
            item.title = "Cambiar a grados"
            supportActionBar!!.title = "Calculator App | RAD"
        }
        modeloCalculadora.setEstado()
    }

    private fun cambiandoDisplay2() {
        val operacionActual = display2.text.split(" ")

        if ( operacionActual.size > 1 )  {
            display2.text = ("${operacionActual[0]} ${operacionActual[1]} ${display.text}")
        } else {
            display2.text = display.text
        }
    }


    private fun showToast() {
        Toast.makeText(this, "Operación no válida", Toast.LENGTH_SHORT).show()
    }
}