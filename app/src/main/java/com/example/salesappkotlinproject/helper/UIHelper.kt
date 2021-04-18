package com.example.salesappkotlinproject.helper

import android.content.Context
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import com.example.salesappkotlinproject.R
import com.google.android.material.snackbar.Snackbar

fun String.toSom() = "$this сом"
fun String.toSht() = "$this шт."

fun EditText.isEmptyInputData(message: String): Boolean {
    if (this.text.isNullOrEmpty()) {
        this.error = message
        return true
    }
    return false
}

fun showActionSnackbar(
    view: View,
    message: String,
    actionTitle: String,
    action: () -> Unit,
    context: Context
) {
    Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction(actionTitle){
        action()
    }.setActionTextColor(ContextCompat.getColor(context, R.color.color_main_blue)).show()
}