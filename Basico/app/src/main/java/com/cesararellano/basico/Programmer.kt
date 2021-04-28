package com.cesararellano.basico

class Programmer(val name: String,
                 var age: Int,
                 val languages: Array<Language>,
                 val friends: Array<Programmer>? = null) {

    enum class Language {
        KOTLIN,
        SWIFT,
        JAVA,
        JAVASCRIPT
    }

    fun code() {
       languages.forEach {
           println("Estoy programando en: $it")
       }
        for( language: Language in languages ) {
            println("Estoy programando en: $language")
        }
    }
}