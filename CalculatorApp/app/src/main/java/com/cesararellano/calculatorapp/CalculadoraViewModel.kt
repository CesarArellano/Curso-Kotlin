package com.cesararellano.calculatorapp

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "CalculadoraViewModel"

class CalculadoraViewModel: ViewModel() {
    private val modeloCalculadora = ModeloCalculadora()
    private lateinit var resultadosArray: Array<String>

    var resultado:String = "0" // Número mostrado en Display 1
    var operacionActual:String = "0" // Número mostrado en Display 2
    var operandoEnMemoria = "Mem: 0" // Número mostrado en Display 3

    init {
        Log.d(TAG, "Instancia de View Model")
    }

    fun agregarNumeroVM(numero:String):Array<String> {
        resultadosArray = modeloCalculadora.agregarNumero(numero)
        resultado = resultadosArray[0]
        return resultadosArray
    }

    fun cambiarSignoVM():String {
        resultado = modeloCalculadora.cambiarSigno()
        return resultado
    }

    fun agregarPuntoDecimalVM():String {
        resultado = modeloCalculadora.agregarPuntoDecimal()
        return resultado
    }

    fun eliminarUltimaEntradaVM():String {
        resultado = modeloCalculadora.eliminarUltimaEntrada()
        return resultado
    }

    fun resetearVM() {
        modeloCalculadora.resetear()
        resultado = "0"
        operacionActual = "0"
        operandoEnMemoria = "Mem: 0"
    }

    fun calcularResultadoVM():Array<String> {
        resultadosArray = modeloCalculadora.calcularResultado()
        resultado = resultadosArray[0]
        return resultadosArray
    }

    fun operadorSeleccionadoVM(nuevoOperador: String, operacionDeUnOperando: Boolean, longitudOperacionActual:Int = 0):Array<String> {
        resultadosArray = modeloCalculadora.operadorSeleccionado(nuevoOperador, operacionDeUnOperando, longitudOperacionActual)
        resultado = resultadosArray[0]
        return resultadosArray
    }

    fun operacionEnMemoriaVM(operacion:String):String {
        operandoEnMemoria = modeloCalculadora.operacionEnMemoria(operacion)
        return operandoEnMemoria
    }

    fun getEstadoVM():Boolean {
        return modeloCalculadora.getEstado()
    }

    fun setEstadoVM() {
        modeloCalculadora.setEstado()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "El View Model está por destruirse")
    }
}