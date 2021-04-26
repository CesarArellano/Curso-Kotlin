package com.cesararellano.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        variablesConstantes()
    }

    private fun variablesConstantes() {
        // Variables

        var myFirstVariable = "Hello César"
        var myFirstNumber = 1

        // No se pueden reasignar variables con diferentes tipos, se respeta el tipado
        // myFirstVariable = myFirstNumber

        println(myFirstVariable)
        println(myFirstNumber)
    }

}