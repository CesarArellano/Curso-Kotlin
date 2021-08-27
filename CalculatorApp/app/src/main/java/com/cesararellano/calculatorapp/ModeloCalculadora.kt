package com.cesararellano.calculatorapp

import kotlin.math.*

class ModeloCalculadora {
    private var operandoEnMemoria = "0"
    private var operandoEnEspera:String = "0" // Segundo operador
    private var operando:String = "0" // Primer Operador
    private var operacionEnEsperaDeOperando = ""
    private var resultado:String = "0"

    fun resetear() {
        operandoEnEspera = "0"
        operando = "0"
        operacionEnEsperaDeOperando = ""
        resultado = "0"
        operandoEnMemoria = "0"
    }

    fun agregarNumero(number: String): Array<String> {
        var error = "false"

        when(resultado) {
            "0" -> resultado = number
            "-0" -> resultado = "-$number"
            else -> {
                resultado += number
                if( number == "𝛑") {
                    error = "true"
                    resultado = "0"
                }

            }
        }

        return arrayOf(resultado, error)
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

    fun operadorSeleccionado(nuevoOperador: String, operacionActual:String, operacionUnOperando: Boolean):Array<String> {
        val textoDisplay2:String

        if(operacionUnOperando) {
            operacionEnEsperaDeOperando = nuevoOperador
            operando = resultado
            textoDisplay2 = ""
        } else {
            if( operacionEnEsperaDeOperando == "") {
                operacionEnEsperaDeOperando = nuevoOperador
                operando = resultado
                textoDisplay2 = "$operando $nuevoOperador"
            } else {
                val operacionActualSplit = operacionActual.split(" ")
                println(operacionActualSplit)
                if( operacionActualSplit.size == 2) {
                    textoDisplay2 = "$operando $operacionEnEsperaDeOperando $resultado"
                } else {
                    operando = operacionActualSplit[0]
                    resultado = operacionActualSplit[2]
                    val resultadoFinal = this.calcularResultado()[0]
                    operacionEnEsperaDeOperando = nuevoOperador
                    textoDisplay2 = "$resultadoFinal $nuevoOperador"
                }
            }
        }


        resultado = "0"
        return arrayOf("0", textoDisplay2)
    }

    fun calcularResultado(): Array<String> {
        var error = "false"
        val number1: Double = if (operando == "𝛑") 3.1416 else operando.toDouble()
        val number2: Double = if (operando == "𝛑") 3.1416 else resultado.toDouble()

        when (operacionEnEsperaDeOperando) {
            "+" -> resultado = "${ number1 + number2 }"
            "-" -> resultado = "${ number1 - number2 }"
            "*" -> resultado = "${ number1 * number2 }"
            "/" -> {
                if (number2 != 0.0) {
                    resultado = "${ number1 / number2 }"
                } else {
                    error = "true"
                }
            }
            "xⁿ" -> resultado = "${ number1.pow(number2) }"
            "ⁿ√" -> resultado = "${ number2.pow(1 / number1) }"
            "sin" ->  resultado = "${ sin( number1 ) }"
            "cos" ->  resultado = "${ cos( number1 ) }"
            "√" -> resultado = "${ sqrt( number1 ) }"
            "10ⁿ"-> resultado = "${10.0.pow(number1)}"
            "1/X" -> resultado = "${ 1/number1 }"
            else -> resultado = "0"
        }

        operacionEnEsperaDeOperando = ""
        operandoEnEspera = resultado

        if(resultado.length > 10) {
            resultado = resultado.substring(0, resultado.length - 10)
        }

        if (resultado.endsWith(".0")) {
            resultado = resultado.substring(0, resultado.length - 2)
        }

        return arrayOf(resultado, error)
    }

    fun operacionEnMemoria(operacion:String, numeroEnDisplay:String = "" ):String {
        operandoEnMemoria = when(operacion) {
            "Store" -> numeroEnDisplay
            "Recall" -> return operandoEnMemoria
            "MC" -> "0"
            "Mem+" -> "${ operandoEnMemoria.toDouble() + numeroEnDisplay.toDouble() } "
            "Mem-" -> "${ operandoEnMemoria.toDouble() - numeroEnDisplay.toDouble() } "
            else -> "0"
        }
        return numeroEnDisplay
    }
}