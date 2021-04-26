package com.cesararellano.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Lección 1: variablesConstantes()
        // Lección 2: tiposDeDatos()
        // Lección 3 : sentenciaIf()
        // Lección 4:
        setenciaWhen()
    }

    private fun variablesConstantes() {
        // Variables

        var myFirstVariable = "Hello César"
        var myFirstNumber = 1

        // No se pueden reasignar variables con diferentes tipos, se respeta el tipado
        // myFirstVariable = myFirstNumber

        println(myFirstVariable)
        println(myFirstNumber)


        // Constantes

        val urlApi = "https://helloworld.com"
        println(urlApi)

    }

    private fun tiposDeDatos() {
        // String
        val myString:String = "Hello Kotlin"
        val myString2:String = "Welcome to Android Studio"
        val myString3:String = myString + " " + myString2
        println(myString3)

        // Enteros (Byte, Short, Int, Long)
        val myInt:Int = 1
        val myInt2:Int = 2
        val myInt3:Int = myInt + myInt2
        println(myInt3)

        // Decimales (Float, Double)
        val myDouble: Double = 5.9
        val myDouble2: Double = 2.8
        val myDouble3: Double = myDouble + myDouble2
        println(myDouble3)

        // Boolean (Bool)
        val myBool: Boolean = true
        val myBool2: Boolean = false
        val myBool3: Boolean = myBool && myBool2

        println(myBool3)
    }

    private fun sentenciaIf() {
        val myNumber = 19
        if (myNumber < 10) {
            println("$myNumber es menor que 10")
        } else if(myNumber == 10) {
            println("$myNumber es igual que 10")
        } else {
            println("$myNumber es mayor que 10")
        }
    }

    // When es como Switch
    fun setenciaWhen() {
        // When con String
        val country = "México"

        when (country) {
            "España", "México", "Perú", "Argentina" -> {
                println("El idioma es español")
            } "EU" -> {
                println("Hello United States")
            } "Canadá" -> {
                println("Hello Canada")
            } else -> {
                println("No sé de donde eres bro")
            }
        }

        // When con Int
        val age = 19

        when(age) {
            0, 1, 2 -> {
                println("Eres un bebé")
            } in 3 .. 10 -> {
                println("Eres un niño")
            } in 11 .. 17 -> {
                println("Eres un adolescente")
            } in 18 .. 69 -> {
                println("Eres un adulto")
            } in 78 .. 9 -> {
                println("Eres anciano")
            } else ->{
                println("Esto no es normal viejo D:")
            }
        }

    }

}