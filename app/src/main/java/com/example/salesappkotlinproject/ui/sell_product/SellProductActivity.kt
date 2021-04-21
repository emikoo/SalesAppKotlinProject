package com.example.salesappkotlinproject.ui.sell_product

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.salesappkotlinproject.R
import com.example.salesappkotlinproject.helper.showToast
import com.example.salesappkotlinproject.model.Product
import com.example.salesappkotlinproject.ui.bottom_nav.product_list.ProductListFragment.Companion.product_detail
import com.example.salesappkotlinproject.ui.bottom_nav.product_list.view_model.ProductViewModel
import kotlinx.android.synthetic.main.activity_sell_product.*
import java.time.YearMonth
import java.util.*

class SellProductActivity : AppCompatActivity() {

    private lateinit var viewModel: ProductViewModel
    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell_product)

        viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        initView()
        setupListener()
    }

    private fun initView() {
        product = intent.getSerializableExtra(product_detail) as Product
        val soldNumber = 1

        sell_item_name.text = product.name
        sold_price.text = product.sale_price.toString()
        et_sold_number.setText(soldNumber.toString())
    }

    private fun setupListener() {
        btn_close.setOnClickListener {
            onBackPressed()
        }
        btn_calendar.setOnClickListener {
            showDatePickerDialog()
        }
        sell_negative.setOnClickListener {
            onBackPressed()
        }
        sell_positive.setOnClickListener {
            saveEdits()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val time = calendar.time

        val dialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view, mYear, mMonth, mDay ->
            val month = mMonth+1
            tv_date.setText("$mDay/$month/$mYear")
            //сохранять добавленную дату в продукт солддейт
        }, year, month, day)
        dialog.show()
    }

    private fun saveEdits() {
        if (tv_date.text.isNotEmpty() ){
            val date = tv_date.text
            val soldNumber = et_sold_number.text.toString().toInt()
            val number = product.count
            val availableNumber = number - soldNumber
            val sold = true

            product = Product(product.name, product.sale_price, product.cost_price, product.date, product.count,
            availableNumber, sold, soldNumber)

            viewModel.updateProduct(product)
            onBackPressed()
        } else showToast(this, "Заполните дату продажи")
    }
}