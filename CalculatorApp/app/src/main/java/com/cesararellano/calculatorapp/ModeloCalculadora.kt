package com.cesararellano.calculatorapp

import kotlin.math.*

class ModeloCalculadora {
    private var operandoEnEspera:String = "0" // Segundo operador
    private var operando:String = "0" // Primer Operador
    private var operacionEnEsperaDeOperando = "+"
    private var resultado:String = "0"

    fun resetear() {
        operandoEnEspera = "0"
        operando = "0"
        operacionEnEsperaDeOperando = "+"
        resultado = "0"
    }

    fun agregarNumero(number: String): String {
        if (resultado == "0") {
            resultado = number
            return resultado
        }

        if (resultado == "-0") {
            resultado = "-$number"
            return resultado
        }
        resultado += number
        return resultado
    }

    fun agregarPuntoDecimal():String {
        if (resultado.contains('.')) return resultado

        resultado = if (resultado.startsWith("0")) {
            "0."
        } else {
            "$resultado."
        }

        return resultado
    }

    fun cambiarSigno():String {
        resultado = if (resultado.startsWith("-")) {
            resultado.replace("-", "")
        } else {
            "-$resultado"
        }
        return resultado
    }

    fun eliminarUltimaEntrada():String {
        resultado = if ( resultado.replace("-", "").length > 1 ) {
            resultado.substring(0, resultado.length - 1)
        } else {
            "0"
        }
        return resultado
    }

    fun operadorSeleccionado(nuevoOperador: String):String {
        operacionEnEsperaDeOperando = nuevoOperador
        operando= resultado
        resultado = "0"
        return resultado
    }

    fun calcularResultado(): String {
        val number1: Double = operando.toDouble()
        val number2: Double = resultado.toDouble()

        when (operacionEnEsperaDeOperando) {
            "+" -> resultado = "${ number1 + number2 }"
            "-" -> resultado = "${ number1 - number2 }"
            "*" -> resultado = "${ number1 * number2 }"
            "/" -> {
                if (number2 != 0.0) {
                    resultado = "${ number1 / number2 }"
                }
            }
            "xⁿ" -> resultado = "${ number1.pow(number2) }"
            "ⁿ√" -> resultado = "${ number2.pow(1 / number1) }"
            "sin" ->  resultado = "${ sin( number1 ) }"
            "cos" ->  resultado = "${ cos( number1 ) }"
            "√" -> resultado = "${ sqrt( number1 ) }"
            "10ⁿ"-> resultado = "${10.0.pow(number1)}"
            "1/X" -> resultado = "${ 1/number1 }"
        }

        operandoEnEspera = resultado

        if(resultado.length > 10) {
            resultado = resultado.substring(0, resultado.length - 10)
        }

        if (resultado.endsWith(".0")) {
            resultado = resultado.substring(0, resultado.length - 2)
        }

        return resultado
    }

}