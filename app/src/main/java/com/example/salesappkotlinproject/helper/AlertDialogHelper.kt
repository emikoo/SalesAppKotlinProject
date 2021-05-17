package com.example.salesappkotlinproject.helper

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.salesappkotlinproject.R
import com.example.salesappkotlinproject.data.model.Product
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

//fun showAddingProductActionDialog(
//    context: Context,
//    fragment: Fragment
//) {
//    val alert = AlertDialog.Builder(context, R.style.ProductDialogStyle)
//
//    val inflater = fragment.layoutInflater.inflate(R.layout.alert_add, null)
//    alert.setView(inflater)
//    val negativeButton: Button = inflater.findViewById(R.id.negative)
//    val positiveButton: Button = inflater.findViewById(R.id.positive)
//
//    val nameEditText: EditText = inflater.findViewById(R.id.et_name)
//    val priceEditText: EditText = inflater.findViewById(R.id.et_price)
//    val costPriceEdiText: EditText = inflater.findViewById(R.id.et_cost_price)
//    val numberEditText: EditText = inflater.findViewById(R.id.et_number)
//
//    fun errorString(resId: Int) = fragment.getString(resId)
//
//    val dialog = alert.create()
//
//    negativeButton.setOnClickListener {
//        dialog.dismiss()
//    }
//    positiveButton.setOnClickListener {
//        var error = 0
//        if (nameEditText.isEmptyError(errorString(R.string.add_name))) error += 1
//        if (priceEditText.isEmptyError(errorString(R.string.add_price))) error += 1
//        if (costPriceEdiText.isEmptyError(errorString(R.string.add_cost_price))) error += 1
//        if (numberEditText.isEmptyError(errorString(R.string.add_incoming_number))) error += 1
//
//        if (error > 0) return@setOnClickListener
//
//
//        dialog.dismiss()
//    }
//    dialog.show()
//}
