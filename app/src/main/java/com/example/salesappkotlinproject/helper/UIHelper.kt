package com.example.salesappkotlinproject.helper

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.salesappkotlinproject.R
import com.example.salesappkotlinproject.data.model.Product
import com.example.salesappkotlinproject.ui.owner.bottom_nav.product_list.ProductListFragment
import com.example.salesappkotlinproject.ui.owner.sell_product.SellProductActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.alert_sell.*

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

var toast: Toast? = null
fun showToast(context: Context, message: String){
    if (toast != null) toast?.cancel()
    toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
    toast?.show()
}


