package com.example.salesappkotlinproject.ui.sell_product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.salesappkotlinproject.R
import com.example.salesappkotlinproject.model.Product
import com.example.salesappkotlinproject.ui.detail_product.DetailProductActivity
import com.example.salesappkotlinproject.ui.product_list.ProductListFragment.Companion.product_detail
import kotlinx.android.synthetic.main.activity_sell_product.*

class SellProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell_product)

        initView()

        setupListener()
    }

    private fun initView() {
        val product = intent.getSerializableExtra(product_detail) as Product
        var soldNumber = 1

        sell_item_name.text = product.name
        et_sold_price.hint = product.sale_price.toString()
        et_sold_number.hint = soldNumber.toString()
    }

    private fun setupListener() {
        btn_close.setOnClickListener {
            onBackPressed()
        }
        sell_negative.setOnClickListener {
            onBackPressed()
        }
        sell_positive.setOnClickListener {

        }
    }
}