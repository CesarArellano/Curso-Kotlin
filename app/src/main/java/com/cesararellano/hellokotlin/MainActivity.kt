package com.cesararellano.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Lección 1: variablesConstantes()
        // Lección 2: tiposDeDatos()
        // Lección 3: sentenciaIf()
        // Lección 4: setenciaWhen()
        // Lección 5: arrays()
        // Lección 6
        maps() // Similar a los diccionarios (Objetos literales)
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

    fun arrays() {
        val name = "Brais"
        val surname = "Moure"
        val company = "MoureDev"
        val age = "32"

        // Creación de un Array
        val myArray: ArrayList<String> = arrayListOf<String>()

        // Añadir datos de uno en uno o todos
        myArray.add(name)
        myArray.addAll(listOf(name, surname, company, age))
        println(myArray)
        println(myArray[myArray.size - 1])

        // Eliminar elementos en un arreglo
        myArray.removeAt(myArray.size - 1)
        println(myArray[myArray.size - 1])

        // Recorrer arreglo
        myArray.forEach {
            println(it);
        }

        println(myArray.size)
        myArray.clear()
        println(myArray.count())
        // myArray.first()
        // myArray.last()
        // myArray.sort()
        println(myArray)
    }

    fun maps() {
        var myMap: Map<String, String> = mapOf();
        println(myMap)

        // Añadir elementos
        // Mapa no mutable myMap = mapOf("nombre" to "César", "edad" to "21", "desarrollador" to "true")
        myMap = mutableMapOf("nombre" to "César", "edad" to "21", "desarrollador" to "true")
        myMap["apellido"] = "Arellano"
        myMap.put("relacion", "true")
        println(myMap)
        // Actualizando un dato
        myMap.put("nombre", "Mauricio")
        println(myMap["nombre"])

        // Eliminar dato
        myMap.remove("nombre")
        println(myMap["nombre"])


    }
}