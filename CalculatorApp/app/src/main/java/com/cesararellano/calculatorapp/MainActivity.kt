package com.cesararellano.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.view.Menu
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView // Muestra el operador actual o resultado de la operación
    private lateinit var display2: TextView // Muestra la operación en tiempo real.
    private lateinit var display3: TextView // Muestra el operando en memoria.
    private val calculadoraViewModel: CalculadoraViewModel by lazy {
        ViewModelProvider(this).get(CalculadoraViewModel::class.java)
    }

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_main )
        display  = findViewById( R.id.display )
        display2 = findViewById( R.id.display2 )
        display3 = findViewById( R.id.display3 )
    }

    // En esta función se restablecen los valores que preserva el ViewModel.
    override fun onStart() {
        super.onStart()
        display.text = calculadoraViewModel.resultado
        display2.text = calculadoraViewModel.operacionActual
        display3.text = calculadoraViewModel.operandoEnMemoria
        // Seteamos el título del ActionBar dependiendo de su estado.
        if( calculadoraViewModel.getEstadoVM() )  {
            supportActionBar!!.title = "Calculator App | RAD"
        } else {
            supportActionBar!!.title = "Calculator App | DEG"
        }
    }

    // En esta función se almacenan los valores en el ViewModel, antes de que se destruya MainActivity tras rotar el teléfono y así mantenga el estado.
    override fun onDestroy() {
        super.onDestroy()
        calculadoraViewModel.resultado = display.text.toString()
        calculadoraViewModel.operacionActual = display2.text.toString()
        calculadoraViewModel.operandoEnMemoria = display3.text.toString()
    }

    // Creamos el menú para que se coloque en el ActionBar y podamos cambiar la calculadora a radianes o grados.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return true
    }

    // Esta función se encarga de refrescar en pantalla el operando actual, después de ser modificado en el modelo.
    fun agregarNumeroCtrl(boton: View) {
        val numero = (boton as Button).text.toString()
        val resultados:Array<String> = calculadoraViewModel.agregarNumeroVM(numero)

        if( resultados[1] == "true" ) showToast()

        display.text = resultados[0]
        cambiandoDisplay2()
    }

    // Agrega un punto decimal al operando actual y lo refresca en pantalla.
    @Suppress( "UNUSED_PARAMETER") // Ponemos este decorador debido a que no se ocupa el botón dentro de la función.
    fun agregarPuntoDecimalCtrl(boton: View) {
        display.text = calculadoraViewModel.agregarPuntoDecimalVM()
        cambiandoDisplay2()
    }

    // Cambia el signo del operando actual a positivo o negativo.
    @Suppress( "UNUSED_PARAMETER")
    fun cambiarSignoCtrl(boton: View) {
        display.text = calculadoraViewModel.cambiarSignoVM()
        cambiandoDisplay2()
    }

    // Esta función elimina la última entrada añadida en el operando actual.
    @Suppress( "UNUSED_PARAMETER")
    fun eliminarUltimaEntradaCtrl(boton: View) {
        display.text = calculadoraViewModel.eliminarUltimaEntradaVM()
        cambiandoDisplay2()
    }

    // En esta función se limpia la memoria, operaciones pendientes y se refresca la pantalla a "0".
    @Suppress( "UNUSED_PARAMETER")
    fun resetearCtrl(boton: View) {
        calculadoraViewModel.resetearVM()
        display.text = "0"
        display2.text = "0"
        display3.text = ("Mem: 0")
    }

    // Tras dar al botón = esta función calcula el resultado final.
    @Suppress("UNUSED_PARAMETER")
    fun calcularResultadoCtrl(boton: View) {
        val resultados:Array<String> = calculadoraViewModel.calcularResultadoVM()

        if( resultados[1] == "true") showToast() // Muestra un Toast si ocurrió un error en el método calcularResultado()

        display.text = resultados[0]
        display2.text = "0"
    }

    // Esta función se encarga de calcular o añadir la operación que tocó en pantalla.
    fun operadorSeleccionadoCtrl(boton: View) {
        val nuevoOperador = (boton as Button).text.toString()

        if ( !nuevoOperador.matches( Regex("[+-/*]") ) && nuevoOperador != "xⁿ" && nuevoOperador != "ⁿ√" ) { // Si es verdadero, signfica que es una operación de un operando
            calculadoraViewModel.operadorSeleccionadoVM(nuevoOperador, true)
            val resuladosCalcular:Array<String> = calculadoraViewModel.calcularResultadoVM()
            if( resuladosCalcular[1] == "true" ) showToast() // Muestra un Toast si ocurrió un error en el método calcularResultado()
            display.text = resuladosCalcular[0]
            display2.text = "0"
        } else { // Operación de dos operandos
            val longitudOperacionActual:Int = display2.text.split(" ").size // Se obtiene la longitud de la operación actual para que el modelo decida que debe pintar en pantalla.
            val resultadosOperador:Array<String> = calculadoraViewModel.operadorSeleccionadoVM(nuevoOperador, false, longitudOperacionActual)

            if( resultadosOperador[2] == "true" ) showToast() // Muestra un Toast si ocurrió un error en el método operadorSeleccionado()

            display.text = resultadosOperador[0]
            display2.text = resultadosOperador[1]
        }
    }

    // Función dedicada para manejar el operando en memoria.
    fun operacionEnMemoriaCtrl(boton: View) {
        val operacion = (boton as Button).text.toString()
        val opcionRecall:String = getString(R.string.botonRecall) // Se obtiene el String del botón Recall, ya que es la única operación de memoria que refresca el display 1 y 2.

        if( operacion == opcionRecall ) { // Será verdadero, si presionó el botón Recall.
            display.text = calculadoraViewModel.operacionEnMemoriaVM(operacion) // Realiza la operación adecuada en memoria.
            cambiandoDisplay2()
        } else {
            val operandoEnMemoria:String = calculadoraViewModel.operacionEnMemoriaVM(operacion) // Realiza la operación adecuada en memoria.
            display3.text = ("Mem: $operandoEnMemoria")
        }
    }

    // Cambia el modo de la calculadora a grados o radianes.
    @Suppress("UNUSED_PARAMETER")
    fun cambiarGradosRadianes(item: android.view.MenuItem) {
        if( calculadoraViewModel.getEstadoVM() ) {
            supportActionBar!!.title = "Calculator App | DEG" // Cambia el título del ActionBar
        } else {
            supportActionBar!!.title = "Calculator App | RAD"
        }
        calculadoraViewModel.setEstadoVM() // Cambia el modo de la calculadora.
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