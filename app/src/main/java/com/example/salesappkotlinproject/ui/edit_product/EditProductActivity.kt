package com.example.salesappkotlinproject.ui.edit_product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.salesappkotlinproject.R
import com.example.salesappkotlinproject.model.Product
import com.example.salesappkotlinproject.ui.bottom_nav.product_list.ProductListFragment
import kotlinx.android.synthetic.main.activity_edit_product.*

class EditProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_product)

        initViews()
        setupListener()
    }

    private fun initViews() {
        val product = intent.getSerializableExtra(ProductListFragment.product_detail) as Product
        edit_item_name.setText(product.name)
        edit_available_count.setText(product.available_count.toString())
        edit_count.setText(product.count.toString())
        edit_sold_count.setText(product.count_sold.toString())
        edit_price.setText(product.sale_price.toString())
        edit_cost_price.setText(product.cost_price.toString())
    }

    private fun setupListener() {
        btn_edit_cancel.setOnClickListener {
            onBackPressed()
        }
        btn_edit_close.setOnClickListener {
            onBackPressed()
        }
        btn_edit_save.setOnClickListener {
//            val product = intent.getSerializableExtra(ProductListFragment.product_detail) as Product
//
//            val name = edit_item_name.text.toString()
//            val availableCount = edit_available_count.text.toString().toInt()
//            val count = edit_count.text.toString().toInt()
//            val soldCount = edit_sold_count.text.toString().toInt()
//            val price = edit_price.text.toString().toInt()
//            val costPrice = edit_cost_price.text.toString().toInt()
//
//            product.name = name
//            product.available_count = availableCount
//            product.count = count
//            product.count_sold = soldCount
//            product.sale_price = price
//            product.cost_price = costPrice
//
//
        }
    }
}