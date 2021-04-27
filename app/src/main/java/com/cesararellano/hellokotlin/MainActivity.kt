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
        // Lección 6 maps() // Similar a los diccionarios (Objetos literales)
        // Lección 7: loops()
        // Lección 8: nullSafety()
        // Lección 9:
        funciones(name = "Cesar", age = 21);
        val sum = suma(num1 = 10, num2 = 203)
        println(sum)
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

    private  fun loops() {
        // Bucles
        val myArray: List<String> = listOf("Hola", "Bienvenido a Android Studio", "César")
        val myMap: MutableMap<String, Int> = mutableMapOf("César" to 1, "Pedro" to 2, "Ruls" to 3)

        // For
        for (myString in myArray) {
            println(myString)
        }

        for (myElement in myMap) {
            println("${myElement.key} - ${myElement.value}")
        }

        for (x: Int in 1..10) {
            println(x)
        }

        for (x: Int in 0 until 10) {
            println(x)
        }

        for (x: Int in 0..10 step 2) {
            println(x)
        }

        val myNumericArray: IntRange = (0..20)
        for( myNum: Int in myNumericArray) {
            println(myNum)
        }

        // While
        var x = 0;
        while (x < 10) {
            println(x)
            x++;
        }
    }
    fun nullSafety() {
        var myString = "RayWayDay"
        // myString = null Esto da error
        println(myString)

        // Variable null safety
        var mySafetyString:String? = "RayWayDay null safety"
        mySafetyString = null;
        println(mySafetyString)

        mySafetyString = "Mauricio";
        println(mySafetyString)

        if (mySafetyString != null) {
            // println(mySafetyString!!) // Aseguro que nunca será nula, a discreción del programador
            println(mySafetyString)
        }

        //  Safe call
        println(mySafetyString?.length) // ? si es null, no ejecuta esta instrucción
        mySafetyString?.let {
            println(it!!)
        } ?: run {
            println(mySafetyString)
        }
    }

    fun funciones(name: String, age: Int) {
        println("Hola, mi nombre es: $name y tengo $age años")
    }

    fun suma(num1: Int, num2: Int): Int {
        return num1 + num2
    }
}