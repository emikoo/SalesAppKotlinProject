//package com.example.salesappkotlinproject.ui.edit_product
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import androidx.lifecycle.ViewModelProvider
//import com.example.salesappkotlinproject.R
//import com.example.salesappkotlinproject.model.Product
//import com.example.salesappkotlinproject.ui.bottom_nav.product_list.ProductListFragment
//import com.example.salesappkotlinproject.ui.bottom_nav.product_list.view_model.ProductViewModel
//import kotlinx.android.synthetic.main.activity_edit_product.*
//
//class EditProductActivity : AppCompatActivity() {
//
//    private lateinit var product: Product
//    private lateinit var viewModel: ProductViewModel
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_edit_product)
//
//        product = intent.getSerializableExtra(ProductListFragment.product_detail) as Product
//        viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
//        initViews()
//        setupListener()
//    }
//
//    private fun initViews() {
//        edit_item_name.setText(product.name)
//        edit_available_count.setText(product.available_count.toString())
//        edit_count.setText(product.count.toString())
//        edit_price.setText(product.sale_price.toString())
//        edit_cost_price.setText(product.cost_price.toString())
//    }
//
//    private fun setupListener() {
//        btn_edit_cancel.setOnClickListener {
//            onBackPressed()
//        }
//        btn_edit_close.setOnClickListener {
//            onBackPressed()
//        }
//        btn_edit_save.setOnClickListener {
//            saveEdits()
//            onBackPressed()
//        }
//    }
//
//    private fun saveEdits() {
//        val name = edit_item_name.text.toString()
//        val availableCount = edit_available_count.text.toString().toInt()
//        val count = edit_count.text.toString().toInt()
//        val price = edit_price.text.toString().toInt()
//        val costPrice = edit_cost_price.text.toString().toInt()
//
//        product.name = name
//        product.available_count = availableCount
//        product.count = count
//        product.sale_price = price
//        product.cost_price = costPrice
//
//        viewModel.insertProduct(product)
//    }
//}