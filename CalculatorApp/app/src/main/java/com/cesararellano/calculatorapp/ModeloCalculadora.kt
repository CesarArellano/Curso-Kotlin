package com.cesararellano.calculatorapp

import kotlin.math.*

class ModeloCalculadora {
    private var operandoEnMemoria = "0"
    private var operandoEnEspera:String = "0" // Segundo operador
    private var operando:String = "0" // Primer Operador
    private var operacionEnEsperaDeOperando = ""
    private var resultado:String = "0"
    private val numeroPI = PI

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
            "𝛑" -> {
                error ="true"
                resultado = "0"
            }
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
            if (resultado != "𝛑") "$resultado." else resultado
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
                    operando = resultadoFinal
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

        val numero1: Double = if (operando == "𝛑") numeroPI else operando.toDouble()
        val numero2: Double = if (resultado == "𝛑") numeroPI else resultado.toDouble()
        println(numero1)

        when (operacionEnEsperaDeOperando) {
            "+" -> resultado = "${ numero1 + numero2 }"
            "-" -> resultado = "${ numero1 - numero2 }"
            "*" -> resultado = "${ numero1 * numero2 }"
            "/" -> {
                if (numero2 != 0.0) {
                    resultado = "${ numero1 / numero2 }"
                } else {
                    error = "true"
                }
            }
            "xⁿ" -> resultado = "${ numero1.pow(numero2) }"
            "ⁿ√" -> resultado = "${ numero2.pow(1 / numero1) }"
            "sin" ->  resultado = "${ sin( numero1 ) }"
            "cos" ->  resultado = "${ cos( numero1 ) }" //cos( Math.toRadians(numero1) in degress
            "√" -> resultado = "${ sqrt( numero1 ) }"
            "10ⁿ"-> resultado = "${10.0.pow(numero1)}"
            "1/X" -> {
                if (numero1 != 0.0) {
                    resultado = "${1 / numero1}"
                } else {
                    error = "true"
                }
            }
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