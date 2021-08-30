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

    /*
    * @function getEstado
    * @result Regresa un boolean diciendo si la calculadora está en radianes o no.
    */
    fun getEstado():Boolean {
        return this.estaEnRadianes
    }

    /*
    * @function setEstado
    * @brief En esta función se invierte el modo de la calculadora.
    */
    fun setEstado() {
        this.estaEnRadianes = !this.estaEnRadianes
    }

    /*
    * @function resetear
    * @brief Limpia la memoria, los operandos y el operador.
    */
    fun resetear() {
        operando = "0"
        operacionEnEsperaDeOperando = ""
        resultado = "0"
        operandoEnMemoria = "0"
    }

    /*
    * @function agregarNumero
    * @brief Esta función agrega el número presionado al operando actual.
    * @param number Es el número presionado en pantalla.
    * @result Regresa un array con el operando actual y una flag de error.
    */
    fun agregarNumero(numero: String): Array<String> {
        var error = "false"

        if( resultado.length >= 14 ) return arrayOf(resultado, "true") // Si es superior a 14 la longitud del operando actual, no se agregará nada.

        if( resultado.contains("E") ) { // Si el operando actual tiene un exponente, ocurrirá un error.
            resultado = "0"
            return arrayOf(resultado, "true")
        }

        when( resultado ) { // Dependiendo del operando actual, agregará o no el número recibido.
            "0" -> resultado = numero
            "-0" -> resultado = "-$numero"
            "𝛑" -> {
                error ="true"
            }
            "-𝛑" -> {
                error ="true"
            }
            else -> {
                if( numero == "𝛑" ) {
                    error = "true"
                    resultado = "0"
                } else {
                    resultado += numero
                }
            }
        }

        return arrayOf( resultado, error )
    }

    /*
    * @function agregarPuntoDecimal
    * @brief Esta función agrega un punto decimal al operando actual.
    * @result Regresa un string con el operando actual.
    */
    fun agregarPuntoDecimal():String {
        if ( resultado.contains('.') ) return resultado

        resultado = if ( resultado.startsWith("0") ) {
            "0."
        } else {
            if ( resultado == "𝛑" || resultado == "-𝛑" ) resultado else "$resultado."
        }

        return resultado
    }

    /*
    * @function cambiarSigno
    * @brief Esta función agrega o quita el signo negativo al operando actual.
    * @result Regresa un string con el operando actual.
    */
    fun cambiarSigno():String {
        resultado = if ( resultado.startsWith("-") ) {
            resultado.replace("-", "")
        } else {
            "-$resultado"
        }
        return resultado
    }

    /*
    * @function eliminarUltimaEntrada
    * @brief Esta función quita la última entrada del operando actual.
    * @result Regresa un string con el operando actual.
    */
    fun eliminarUltimaEntrada():String {
        resultado = if ( resultado.replace("-", "").length > 1 ) {
            resultado.substring( 0, resultado.length - 1 )
        } else {
            "0"
        }
        return resultado
    }

    /*
    * @function operadorSeleccionado
    * @brief Agrega el operador presionado en memoria o realiza la operación en dado caso de
    * @param nuevoOperador Es la operación nueva que se desea realizar.
    * @param operacionDeUnOperando Indica si es una operación de un operando o no.
    * @param longitudOperacionActual (Es un parámetro opcional) Indica la longitud de la operación actual de dos operandos.
    * @result Regresa un Array<String> del resultado en 0, del texto para el display2 y una flag de error.
    */
    fun operadorSeleccionado(nuevoOperador: String, operacionDeUnOperando: Boolean, longitudOperacionActual:Int = 0):Array<String> {
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
                longitudOperacionActual == 2 -> { // Si se tiene el primer operando y el operador, pero no se ha agregado el segundo operando al display, esta condición lo agregará en el display 2.
                    textoDisplay2 = "$operando $operacionEnEsperaDeOperando $resultado"
                }
                else -> { // Se realiza la operación de los dos operandos en pantalla y se agrega el nuevo operador.
                    val resultadoFinal:Array<String> = this.calcularResultado()
                    error = resultadoFinal[1]

                    if( error == "true" ) { // Si hay un error, seteará el operando y el operador en cero.
                        operando = "0"
                        operacionEnEsperaDeOperando = ""
                        textoDisplay2 = "0"
                    } else { // Se muestra el resultado con el nuevo operador.
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

    /*
    * @function calcularResultado
    * @brief Esta función calcula la operación que el usuario desea realizar.
    * @result Regresa un arreglo de String con el resultado de la operación y un flag por si ocurre un error.
    */
    fun calcularResultado(): Array<String> {
        var error = "false"
        // Si algún operando es pi retorna el numero PI de la librería Math de Kotlin.
        val numero1: Double = when(operando) {
            "𝛑" -> numeroPI
            "-𝛑" -> -numeroPI
            else -> operando.toDouble()
        }

        val numero2: Double = when(resultado) {
            "𝛑" -> numeroPI
            "-𝛑" -> -numeroPI
            else -> resultado.toDouble()
        }

        when (operacionEnEsperaDeOperando) {
            "+" -> resultado = "${ numero1 + numero2 }"
            "-" -> resultado = "${ numero1 - numero2 }"
            "*" -> resultado = "${ numero1 * numero2 }"
            "/" -> {
                if ( numero2 != 0.0 ) {
                    resultado = "${ numero1 / numero2 }"
                    if( resultado.contains(".000")) resultado = "${ round( resultado.toDouble() )}" // Validación para redondear
                } else {
                    resultado = "0"
                    error = "true"
                }
            }
            "xⁿ" -> resultado = "${ numero1.pow(numero2) }"
            "ⁿ√" -> {
                if( numero2 >= 0.0 && numero1 != 0.0 ) {
                    resultado = "${ numero2.pow(1 / numero1) }"
                    if( resultado.contains(".99999999")) resultado = "${ round( resultado.toDouble() )}" // Validación para redondear
                } else {
                    resultado = "0"
                    error = "true"
                }
            }
            "sin" ->  {
                resultado = if( estaEnRadianes ) "${ sin(numero1) }" else "${ sin( Math.toRadians(numero1) ) }" // Math.toRadians dentro de sin o cos dará el resultado en grados.
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
                    if( resultado.contains(".000")) resultado = "${ round( resultado.toDouble() )}" // Validación para redondear
                } else {
                    error = "true"
                }
            }
            ""-> resultado = "$numero2"
            else -> resultado = "0"
        }

        operacionEnEsperaDeOperando = ""

        if( resultado.contains("E") && resultado.length > 14 ) { // Si el resultado tiene un exponente, y es mayor a 14 de longitud se compactará para que quepa en pantalla.
            val compactarResultado = resultado.split("E")
            resultado = compactarResultado[0].substring(0, 8) + "E" + compactarResultado[1] // Se extraen los primeros 9 digítos y se concatena el exponente.
        }

        if ( resultado.endsWith(".0") ) {
            resultado = removerPuntoCero(resultado)
        }

        if( resultado.length > 14 ) { // Si la longitud del resultado es mayor a 14 se compactará para que quepa en pantalla.
            resultado = resultado.substring(0, 13)
        }

        if( resultado == "-0") resultado = "0"

        return arrayOf( resultado, error )
    }

    /*
    * @function operacionEnMemoria
    * @brief Esta función modifica el operando en memoria.
    * @param operacion Es la operación en memoria que quiere realizar el usuario.
    * @result Regresa un string con el nuevo operando en memoria.
    */
    fun operacionEnMemoria(operacion:String):String {
        operandoEnMemoria = when( operacion ) {
            "Store" -> resultado
            "MC" -> "0"
            "Recall" -> operandoEnMemoria
            "Mem+" -> "${ operandoEnMemoria.toDouble() + resultado.toDouble() }"
            "Mem-" -> "${ operandoEnMemoria.toDouble() - resultado.toDouble() }"
            else -> "0"
        }

        if( operandoEnMemoria.endsWith(".0") ) {
            operandoEnMemoria = removerPuntoCero(operandoEnMemoria)
        }

        if( operacion == "Recall" ) {
            resultado = operandoEnMemoria
        }

        return operandoEnMemoria
    }

    /*
    * @function removerPuntoCero
    * @brief Esta función remueve el .0 del operando recibido.
    * @param operando Es el operando al que se le quita el .0
    * @result Regresa un string con el nuevo operando.
    */
    private fun removerPuntoCero( operando:String ): String {
        return operando.substring( 0, operando.length - 2 )
    }
}