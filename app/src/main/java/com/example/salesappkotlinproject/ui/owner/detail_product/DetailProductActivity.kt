package com.example.salesappkotlinproject.ui.owner.detail_product

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.salesappkotlinproject.R
import com.example.salesappkotlinproject.data.model.Product
import com.example.salesappkotlinproject.helper.showToast
import com.example.salesappkotlinproject.helper.toSht
import com.example.salesappkotlinproject.helper.toSom
import com.example.salesappkotlinproject.ui.owner.bottom_nav.product_list.ProductListFragment.Companion.product_detail
import com.example.salesappkotlinproject.ui.owner.edit_product.EditProductActivity
import com.example.salesappkotlinproject.ui.owner.sell_product.SellProductActivity
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

        availableCount.text = product.availableCount.toString().toSht()
        count.text = product.count.toString().toSht()
        soldCount.text = product.countSold.toString().toSht()
        price.text = product.salePrice.toString().toSom()
        costPrice.text = product.costPrice.toString().toSom()
    }

    private fun setupListener() {
        val product = intent.getSerializableExtra(product_detail) as Product
        btn_left_detail.setOnClickListener {
            onBackPressed()
        }

        btn_detail_edit.setOnClickListener {
            val intent = Intent(this, EditProductActivity::class.java)
            intent.putExtra(product_detail, product)
            startActivity(intent)
            finish()
        }

        btn_detail_sell.setOnClickListener {
            if (product.availableCount != 0) {
                val intent = Intent(this, SellProductActivity::class.java)
                intent.putExtra(product_detail, product)
                startActivity(intent)
                finish()
            } else {
                showToast(this, "У вас нет в наличии")
            }
        }

    }
}