package com.cesararellano.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.view.Menu
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView // Muestra el operador actual o resultado de la operación
    private lateinit var display2: TextView // Muestra la operación en tiempo real.
    private lateinit var display3: TextView // Muestra el operando en memoria.
    private val modeloCalculadora = ModeloCalculadora() // Instancia del modelo

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_main )

        supportActionBar!!.title = "Calculator App | RAD" // Seteamos el título del ActionBar en su estado inicial.
        display  = findViewById( R.id.display )
        display2 = findViewById( R.id.display2 )
        display3 = findViewById( R.id.display3 )
    }

    // Creamos el menú para que se coloque en el ActionBar y podamos cambiar la calculadora a radianes o grados.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return true
    }

    // Esta función se encarga de refrescar en pantalla el operando actual, después de ser modificado en el modelo.
    fun agregarNumeroCtrl(boton: View) {
        val number = (boton as Button).text.toString()
        val resultados:Array<String> = modeloCalculadora.agregarNumero(number)

        if( resultados[1] == "true" ) showToast()

        display.text = resultados[0]
        cambiandoDisplay2()
    }

    // Agrega un punto decimal al operando actual y lo refresca en pantalla.
    @Suppress( "UNUSED_PARAMETER") // Ponemos este decorador debido a que no se ocupa el botón dentro de la función.
    fun agregarPuntoDecimalCtrl(boton: View) {
        display.text = modeloCalculadora.agregarPuntoDecimal()
        cambiandoDisplay2()
    }

    // Cambia el signo del operando actual a + o -
    @Suppress( "UNUSED_PARAMETER")
    fun cambiarSignoCtrl(boton: View) {
        display.text = modeloCalculadora.cambiarSigno()
        cambiandoDisplay2()
    }

    // Esta función elimina la última entrada añadida en el operando actual.
    @Suppress( "UNUSED_PARAMETER")
    fun eliminarUltimaEntradaCtrl(boton: View) {
        display.text = modeloCalculadora.eliminarUltimaEntrada()
        cambiandoDisplay2()
    }

    // En esta función se limpia la memoria, operaciones pendientes y se refresca la pantalla a 0.
    @Suppress( "UNUSED_PARAMETER")
    fun resetearCtrl(boton: View) {
        modeloCalculadora.resetear()
        display.text = "0"
        display2.text = "0"
        display3.text = ("Mem: 0")
    }

    // Esta función se encarga de calcular o añadir la operación que tocó en pantalla.
    fun operadorSeleccionadoCtrl(boton: View) {
        val nuevoOperador = (boton as Button).text.toString()

        if ( !nuevoOperador.matches( Regex("[+-/*]") ) && nuevoOperador != "xⁿ" && nuevoOperador != "ⁿ√" ) { // Si es verdadero, signfica que es una operación de un operando
            modeloCalculadora.operadorSeleccionado(nuevoOperador, true)
            val resuladosCalcular:Array<String> = modeloCalculadora.calcularResultado()

            if( resuladosCalcular[1] == "true" ) showToast() // Muestra un Toast si ocurrió un error en el método calcularResultado()

            display.text = resuladosCalcular[0]
            display2.text = "0"
        } else { // Operación de dos operandos
            val longitudOperacionActual:Int = display2.text.split(" ").size // Se obtiene la longitud de la operación actual para que el modelo decida que debe pintar en pantalla.
            val resultadosOperador:Array<String> = modeloCalculadora.operadorSeleccionado(nuevoOperador, false, longitudOperacionActual)

            if( resultadosOperador[2] == "true" ) showToast() // Muestra un Toast si ocurrió un error en el método operadorSeleccionado()

            display.text = resultadosOperador[0]
            display2.text = resultadosOperador[1]
        }
    }

    // Tras dar al botón = esta función calcula el resultado final.
    @Suppress("UNUSED_PARAMETER")
    fun calcularResultadoCtrl(boton: View) {
        val resultados:Array<String> = modeloCalculadora.calcularResultado()

        if( resultados[1] == "true") showToast() // Muestra un Toast si ocurrió un error en el método calcularResultado()

        display.text = resultados[0]
        display2.text = "0"
    }

    // Función dedicada para manejar el operando en memoria.
    fun operacionEnMemoriaCtrl(boton: View) {
        val operacion = (boton as Button).text.toString()
        val opcionRecall:String = getString(R.string.botonRecall) // Se obtiene el String del botón Recall, ya que es la única operación de memoria que refresca el display 1 y 2.

        if( operacion == opcionRecall ) { // Será verdadero, si presionó el botón Recall.
            display.text = modeloCalculadora.getOperandoEnMemoria() // Obtiene operando en memoria del modelo.
            cambiandoDisplay2()
        } else {
            val operandoEnMemoria:String = modeloCalculadora.operacionEnMemoria(operacion) // Realiza la operación adecuada en memoria.
            display3.text = ("Mem: $operandoEnMemoria")
        }
    }

    // Cambia el modo de la calculadora a grados o radianes.
    fun cambiarGradosRadianes(item: android.view.MenuItem) {
        if( modeloCalculadora.getEstado() ) {
            item.title = "Cambiar a radianes" // Cambia el título del menú.
            supportActionBar!!.title = "Calculator App | DEG" // Cambia el título del ActionBar
        } else {
            item.title = "Cambiar a grados"
            supportActionBar!!.title = "Calculator App | RAD"
        }
        modeloCalculadora.setEstado() // Cambia el modo de la calculadora.
    }

    // Refresca el segundo display.
    private fun cambiandoDisplay2() {
        val operacionActual:List<String> = display2.text.split(" ")

        if ( operacionActual.size > 1 )  { // Si el tamaño de la lista es mayor a 1, significa que sólo debe modificarse el segundo operando.
            display2.text = ("${operacionActual[0]} ${operacionActual[1]} ${display.text}")
        } else {
            display2.text = display.text
        }
    }

    // Esta función muestra un Toast, si ocurre un error.
    private fun showToast() {
        Toast.makeText(this, "Operación no válida", Toast.LENGTH_SHORT).show()
    }
}