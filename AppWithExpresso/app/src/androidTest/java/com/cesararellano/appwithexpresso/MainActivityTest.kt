package com.cesararellano.appwithexpresso

import android.content.Intent

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.core.internal.deps.guava.collect.Iterables
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.Test

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get:Rule var intentTestScenarioRule = IntentsTestRule(MainActivity::class.java)

    @Test
    fun ingresoDeDatos() {
        onView( withId(R.id.nombreEditText) ) // Métodos de ViewMatchers
            .perform(typeText("Cesar"), closeSoftKeyboard())


        onView(withId(R.id.maleRB))
            .perform(click())

        onView( withId( R.id.registerButton) )
            .perform(click())

        //onView( withId(R.id.apellidoEditText) )
        //    .perform(typeText("Arellano"), closeSoftKeyboard())
        //    .check(matches(isDisplayed()))

        intending( hasExtraWithKey("NAME"))

        val datosRecibidos: Intent =
            Iterables.getOnlyElement(Intents.getIntents())
        println(datosRecibidos.extras!!.getString("NAME"))
        assertEquals(datosRecibidos.extras!!.getString("NAME"), "Cesar")
    }
}