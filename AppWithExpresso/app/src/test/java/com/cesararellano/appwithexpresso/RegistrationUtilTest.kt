package com.cesararellano.appwithexpresso

import org.junit.Assert.*
import org.junit.Test

class RegistrationUtilTest {

    private val registrationUtil = RegistrationUtil()

    @Test
    fun `empty username returns false`() {
        val result = registrationUtil.validateRegistrationInput(
            "",
            "123",
            "123"
        )
        assertEquals(result, false)
    }

    @Test
    fun `valid username and correctly repeated password returns true`() {
        val result = registrationUtil.validateRegistrationInput(
            "Philipp",
            "12345678",
            "12345678"
        )
        println(result)
        assertEquals(result, true)
    }

    @Test
    fun `username already exists returns false`() {
        val result = registrationUtil.validateRegistrationInput(
            "Carl",
            "123",
            "123"
        )
        assertEquals(result, false)
    }

    @Test
    fun `incorrectly confirmed password returns false`() {
        val result = registrationUtil.validateRegistrationInput(
            "Philipp",
            "123456",
            "abcdefg"
        )
        assertEquals(result, false)
    }

    @Test
    fun `empty password returns false`() {
        val result = registrationUtil.validateRegistrationInput(
            "Philipp",
            "",
            ""
        )

        assertEquals(result, false)
    }

    @Test
    fun `less than 8 characters in password returns false`() {
        val result = registrationUtil.validateRegistrationInput(
            "Philipp",
            "abcdefg",
            "abcdefg"
        )
        assertEquals(result, false)
    }
}