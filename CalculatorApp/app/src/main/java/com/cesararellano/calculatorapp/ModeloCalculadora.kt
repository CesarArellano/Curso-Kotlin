package com.cesararellano.calculatorapp

class ModeloCalculadora {
    private var operandoEnEspera = 0 // Primer operando
    private var operando = 0
    private var operacionEnEsperaDeOperando = ""

    fun setOperando(unOperando: Int) {
        operando = unOperando
    }

    fun ejecutaOperacion(operacion: String): Int {
        ejecutaOperacionEnEspera()
        operacionEnEsperaDeOperando = operacion
        operandoEnEspera = operando
        return operando
    }

    private fun ejecutaOperacionEnEspera() {
        when(operacionEnEsperaDeOperando) {
            "+" -> operando += operandoEnEspera
            "-" -> operando = operandoEnEspera - operando
            "*" -> operando *= operandoEnEspera
            "/" -> {
                if (operando != 0) {
                    operando = operandoEnEspera / operando
                }
            }
        }
    }
}