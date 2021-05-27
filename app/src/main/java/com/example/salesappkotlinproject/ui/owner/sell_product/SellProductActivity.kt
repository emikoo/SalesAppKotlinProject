package com.example.salesappkotlinproject.ui.owner.sell_product

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.salesappkotlinproject.R
import com.example.salesappkotlinproject.data.model.Product
import com.example.salesappkotlinproject.data.model.SoldProduct
import com.example.salesappkotlinproject.helper.showToast
import com.example.salesappkotlinproject.ui.owner.bottom_nav.product_list.ProductListFragment.Companion.product_detail
import com.example.salesappkotlinproject.ui.owner.bottom_nav.product_list.ProductViewModel
import com.example.salesappkotlinproject.ui.owner.bottom_nav.sales_history.SoldProductViewModel
import kotlinx.android.synthetic.main.activity_sell_product.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.*

class SellProductActivity : AppCompatActivity() {

    private lateinit var viewModel: ProductViewModel
    private lateinit var soldViewModel: SoldProductViewModel
    private lateinit var product: Product
    private lateinit var soldProduct: SoldProduct

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell_product)

        viewModel = getViewModel(clazz = ProductViewModel::class)
        soldViewModel = getViewModel(clazz = SoldProductViewModel::class)
        setupView()
        setupListener()
    }

    private fun setupView() {
        product = intent.getSerializableExtra(product_detail) as Product
        val soldNumber = 1
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        sell_item_name.text = product.name
        sold_price.text = product.salePrice.toString()
        et_sold_number.setText(soldNumber.toString())
        tv_date.text = "$day/${month + 1}/$year"
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
            if (product.availableCount >= et_sold_number.text.toString().toInt()) {
                saveEdits()
                onBackPressed()
            } else showToast(this, "У вас недостаточно товара")
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val dialog =
            DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                val month = mMonth + 1
                tv_date.text = "$mDay/$month/$mYear"
            }, year, month, day)

        dialog.show()
    }

    private fun saveEdits() {
        val soldNumber = et_sold_number.text.toString().toInt()
        val beforeSold = product.countSold
        val totalSold = soldNumber + beforeSold

        val number = product.count
        val availableNumber = number - totalSold

        var soldPrice = product.salePrice * soldNumber

        updateProduct(availableNumber, totalSold)
        insertSoldProduct(soldNumber, soldPrice)
    }

    private fun updateProduct(availableNumber: Int, totalSold: Int) {
        if (product.id == 0) product.id = 1
        product = Product(
            product.id,
            product.name,
            product.salePrice,
            product.costPrice,
            product.count,
            availableNumber,
            totalSold
        )
        viewModel.updateProduct(product)
    }

    private fun insertSoldProduct(soldNumber: Int, soldPrice: Int) {
        val date = tv_date.text.toString()
        soldProduct = SoldProduct(
            0,
            product.name,
            product.salePrice,
            product.costPrice,
            soldNumber,
            soldPrice,
            date
        )
        soldViewModel.insertSoldProduct(soldProduct)
    }
}