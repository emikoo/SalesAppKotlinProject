package com.example.salesappkotlinproject.helper

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.salesappkotlinproject.R
import kotlinx.android.synthetic.main.alert_sell.*
import java.util.*
import java.util.function.Function

fun showSellingAlertDialog(
    context: Context,
    fragment: Fragment,
    action: Unit
) {
    val alert = AlertDialog.Builder(context)
    val view: View = fragment.layoutInflater.inflate(R.layout.alert_sell, null)
    alert.setView(view)
        .setCustomTitle(fragment.title_dialog)
        .setCancelable(false)
    alert.setNegativeButton("НЕТ") { dialog, _ ->
        dialog.dismiss()
    }
    alert.setPositiveButton("ДА") { dialog, _ ->
        action
        dialog.dismiss()
    }
    alert.show()
}