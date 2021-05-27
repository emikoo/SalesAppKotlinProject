package com.example.salesappkotlinproject.ui.owner.edit_product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.salesappkotlinproject.R
import com.example.salesappkotlinproject.helper.showToast
import com.example.salesappkotlinproject.data.model.Product
import com.example.salesappkotlinproject.ui.owner.bottom_nav.product_list.ProductListFragment
import com.example.salesappkotlinproject.ui.owner.bottom_nav.product_list.ProductViewModel
import kotlinx.android.synthetic.main.activity_edit_product.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class EditProductActivity : AppCompatActivity() {

    private lateinit var viewModel: ProductViewModel
    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_product)

        viewModel = getViewModel(clazz = ProductViewModel::class)
        initViews()
        setupListener()
    }

    private fun initViews() {
        product = intent.getSerializableExtra(ProductListFragment.product_detail) as Product
        edit_item_name.setText(product.name)
        edit_available_count.text = product.availableCount.toString()
        edit_sold_count.setText(product.countSold.toString())
        edit_count.setText(product.count.toString())
        edit_price.setText(product.salePrice.toString())
        edit_cost_price.setText(product.costPrice.toString())
    }

    private fun setupListener() {
        btn_edit_cancel.setOnClickListener {
            onBackPressed()
        }
        btn_edit_close.setOnClickListener {
            onBackPressed()
        }
        btn_edit_save.setOnClickListener {
            saveEdits()
            onBackPressed()
        }
    }

    private fun saveEdits() {
        val newName = edit_item_name.text.toString()
        val count = edit_count.text.toString().toInt()
        val price = edit_price.text.toString().toInt()
        val soldCount = edit_sold_count.text.toString().toInt()
        val availableNumber = count - soldCount
        val costPrice = edit_cost_price.text.toString().toInt()

//        product.name = newName

//        product = Product(product.id, newName, price, costPrice, product.date, count, availableNumber,
//            product.sold, soldCount, product.soldDate)

        viewModel.updateProduct(product)

        showToast(this, "Товар изменен")
    }
}