package com.example.salesappkotlinproject.helper

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.salesappkotlinproject.R
import com.google.android.material.snackbar.Snackbar

fun showActionSnackbar(
    view: View,
    message: String,
    actionTitle: String,
    action: () -> Unit,
    context: Context
) {
    Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction(actionTitle) {
        action()
    }.setActionTextColor(ContextCompat.getColor(context, R.color.color_main_blue)).show()
}

var toast: Toast? = null
fun showToast(context: Context, message: String) {
    if (toast != null) toast?.cancel()
    toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
    toast?.show()
}


