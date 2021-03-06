package com.example.salesappkotlinproject.helper

import android.widget.EditText

fun String.toSom() = "$this сом"
fun String.toSht() = "$this шт."

fun EditText.isEmptyInputData(message: String): Boolean {
    if (this.text.isNullOrEmpty()) {
        this.error = message
        return true
    }
    return false
}