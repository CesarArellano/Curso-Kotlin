/*
* @file numbers.dart
* @brief En este archivo se aprenderán los tipos de datos númericos
* @author Arellano Velásquez César Mauricio
* @date 02/08/2021
* @param       weight    The weight of the chicken.
* @result      A pointer to the cooked chicken.
*/

package com.cesararellano.calculatorapp

import kotlin.math.*

class ModeloCalculadora {
    // Valor Pi extraído de Kotlin.math
    private val numeroPI = PI
    // Estado inicial
    private var estaEnRadianes = true // Nos indica si estamos en radianes o no.
    private var operandoEnMemoria = "0" // Número mostrado en Display 3
    private var operando:String = "0" // Operando
    private var operacionEnEsperaDeOperando = "" // Operador
    private var resultado:String = "0" // Número mostrado en Display 1

    fun getOperandoEnMemoria():String {
        if( operandoEnMemoria.endsWith(".0") ) {
            operandoEnMemoria = removerPuntoCero(operandoEnMemoria)
        }
        return operandoEnMemoria
    }

    fun getEstado():Boolean {
        return this.estaEnRadianes
    }

    fun setEstado() {
        this.estaEnRadianes = !this.estaEnRadianes
    }

    fun resetear() {
        operando = "0"
        operacionEnEsperaDeOperando = ""
        resultado = "0"
        operandoEnMemoria = "0"
    }

    fun agregarNumero(number: String): Array<String> {
        var error = "false"

        if( resultado.length >= 14 ) return arrayOf(resultado, "true")

        if( resultado.contains("E") ) {
            resultado = "0"
            return arrayOf(resultado, "true")
        }

        when( resultado ) {
            "0" -> resultado = number
            "-0" -> resultado = "-$number"
            "𝛑" -> {
                error ="true"
            }
            else -> {
                if( number == "𝛑" ) {
                    error = "true"
                    resultado = "0"
                } else {
                    resultado += number
                }
            }
        }

        return arrayOf( resultado, error )
    }

    fun agregarPuntoDecimal():String {
        if ( resultado.contains('.') ) return resultado

        resultado = if ( resultado.startsWith("0") ) {
            "0."
        } else {
            if ( resultado != "𝛑" ) "$resultado." else resultado
        }

        return resultado
    }

    fun cambiarSigno():String {
        resultado = if ( resultado.startsWith("-") ) {
            resultado.replace("-", "")
        } else {
            "-$resultado"
        }
        return resultado
    }

    fun eliminarUltimaEntrada():String {
        resultado = if ( resultado.replace("-", "").length > 1 ) {
            resultado.substring( 0, resultado.length - 1 )
        } else {
            "0"
        }
        return resultado
    }

    fun operadorSeleccionado(nuevoOperador: String, operacionDeUnOperando: Boolean, operacionActual:String = ""):Array<String> {
        var error = "false"
        val textoDisplay2:String

        if( operacionDeUnOperando ) {
            operacionEnEsperaDeOperando = nuevoOperador
            operando = resultado
            textoDisplay2 = ""
        } else {
            when {
                operacionEnEsperaDeOperando == "" -> {
                    operacionEnEsperaDeOperando = nuevoOperador
                    operando = resultado
                    textoDisplay2 = "$operando $nuevoOperador"
                }
                operacionActual.split(" ").size == 2 -> {
                    textoDisplay2 = "$operando $operacionEnEsperaDeOperando $resultado"
                }
                else -> {
                    val resultadoFinal:Array<String> = this.calcularResultado()
                    error = resultadoFinal[1]

                    if( error == "true" ) {
                        operando = "0"
                        operacionEnEsperaDeOperando = ""
                        textoDisplay2 = "0"
                    } else {
                        operando = resultadoFinal[0]
                        operacionEnEsperaDeOperando = nuevoOperador
                        textoDisplay2 = "$operando $nuevoOperador"
                    }

                }
            }
        }

        resultado = "0"
        return arrayOf( resultado, textoDisplay2, error )
    }

    fun calcularResultado(): Array<String> {
        var error = "false"

        val numero1: Double = if ( operando == "𝛑" ) numeroPI else operando.toDouble()
        val numero2: Double = if ( resultado == "𝛑" ) numeroPI else resultado.toDouble()

        when (operacionEnEsperaDeOperando) {
            "+" -> resultado = "${ numero1 + numero2 }"
            "-" -> resultado = "${ numero1 - numero2 }"
            "*" -> resultado = "${ numero1 * numero2 }"
            "/" -> {
                if ( numero2 != 0.0 ) {
                    resultado = "${ numero1 / numero2 }"
                } else {
                    error = "true"
                }
            }
            "xⁿ" -> resultado = "${ numero1.pow(numero2) }"
            "ⁿ√" -> {
                if( numero2 >= 0.0 ) {
                    resultado = "${ numero2.pow(1 / numero1) }"
                } else {
                    error = "true"
                }
            }
            "sin" ->  {
                resultado = if( estaEnRadianes ) "${ sin(numero1) }" else "${sin( Math.toRadians(numero1) ) }"
            }
            "cos" -> {
                resultado = if( estaEnRadianes ) "${ cos(numero1) }" else "${ cos( Math.toRadians(numero1)) }"
            }
            "√" -> {
                if( numero1 >= 0.0 ) {
                    resultado = "${ sqrt(numero1) }"
                } else {
                    error = "true"
                }
            }
            "10ⁿ"-> resultado = "${ 10.0.pow(numero1) }"
            "1/X" -> {
                if ( numero1 != 0.0 ) {
                    resultado = "${ 1 / numero1 }"
                } else {
                    error = "true"
                }
            }
            ""-> resultado = "$numero2"
            else -> resultado = "0"
        }

        operacionEnEsperaDeOperando = ""

        if( resultado.contains("E") && resultado.length > 14 ) {
            val compactarResultado = resultado.split("E")
            resultado = compactarResultado[0].substring(0, 8) + "E" + compactarResultado[1]
        }

        if ( resultado.endsWith(".0") ) {
            resultado = removerPuntoCero(resultado)
        }

        if( resultado.length > 14 ) {
            resultado = resultado.substring(0, 14)
        }

        return arrayOf( resultado, error )
    }


    fun operacionEnMemoria(operacion:String):String {
        operandoEnMemoria = when( operacion ) {
            "Store" -> resultado
            "Recall" ->  {
                resultado = if( operandoEnMemoria.endsWith(".0") ) removerPuntoCero(operandoEnMemoria) else operandoEnMemoria
                return resultado
            }
            "MC" -> "0"
            "Mem+" -> "${ operandoEnMemoria.toDouble() + resultado.toDouble() }"
            "Mem-" -> "${ operandoEnMemoria.toDouble() - resultado.toDouble() }"
            else -> "0"
        }
        return resultado
    }

    private fun removerPuntoCero( operando:String ): String {
        return operando.substring( 0, operando.length - 2 )
    }
}