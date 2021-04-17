package com.example.salesappkotlinproject.ui.detail_product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.salesappkotlinproject.R
import com.example.salesappkotlinproject.helper.toSht
import com.example.salesappkotlinproject.helper.toSom
import com.example.salesappkotlinproject.model.Product
import com.example.salesappkotlinproject.ui.product_list.ProductListFragment.Companion.product_detail
import kotlinx.android.synthetic.main.activity_detail_product.*

class DetailProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_product)

        initViews()
        setupListener()
    }

    private fun initViews() {
        val product = intent.getSerializableExtra(product_detail) as Product

        detail_item_name.text = product.name
        availableCount.text = product.available_count.toString().toSht()
        count.text = product.count.toString().toSht()
        soldCount.text = product.count_sold.toString().toSht()
        price.text = product.sale_price.toString().toSom()
        costPrice.text = product.cost_price.toString().toSom()
    }

    private fun setupListener() {
        btn_left_detail.setOnClickListener {
            onBackPressed()
        }

        btn_detail_edit.setOnClickListener {

        }

        btn_detail_sell.setOnClickListener {

        }
    }
}