package com.example.salesappkotlinproject.ui.product_list

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salesappkotlinproject.R
import com.example.salesappkotlinproject.helper.ItemSimpleTouch
import com.example.salesappkotlinproject.helper.isEmptyInputData
import com.example.salesappkotlinproject.model.Product
import com.example.salesappkotlinproject.ui.detail_product.DetailProductActivity
import com.example.salesappkotlinproject.ui.product_list.adapter.ClickListener
import com.example.salesappkotlinproject.ui.product_list.adapter.ProductListAdapter
import com.example.salesappkotlinproject.ui.sell_product.SellProductActivity
import kotlinx.android.synthetic.main.alert_sell.*
import kotlinx.android.synthetic.main.fragment_product_list.*

class ProductListFragment : Fragment(), ClickListener {

    private lateinit var adapter: ProductListAdapter

    private var productArray = mutableListOf<Product>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        addItemAction()
    }

    private fun setupRecyclerView() {
        adapter = ProductListAdapter(this)
        rv_product_list.layoutManager = LinearLayoutManager(requireContext())
        rv_product_list.adapter = adapter

        adapter.addItems(productArray)
    }

    private fun addItemAction() {
        fab_add.setOnClickListener {
            showAddingProductActionDialog()
        }
    }

    private fun showAddingProductActionDialog() {
        val alert = AlertDialog.Builder(requireContext(), R.style.ProductDialogStyle)

        val inflater = layoutInflater.inflate(R.layout.alert_add, null)
        alert.setView(inflater)
        val negativeButton: Button = inflater.findViewById(R.id.negative)
        val positiveButton: Button = inflater.findViewById(R.id.positive)

        val nameEditText: EditText = inflater.findViewById(R.id.et_name)
        val priceEditText: EditText = inflater.findViewById(R.id.et_price)
        val costPriceEdiText: EditText = inflater.findViewById(R.id.et_cost_price)
        val numberEditText: EditText = inflater.findViewById(R.id.et_number)

        val dialog = alert.create()

        negativeButton.setOnClickListener {
            dialog.dismiss()
        }
        positiveButton.setOnClickListener {
            checkField(nameEditText, priceEditText, costPriceEdiText, numberEditText, dialog)
        }
        dialog.show()
    }

    private fun checkField(
        nameEditText: EditText, priceEditText: EditText,
        costPriceEdiText: EditText, numberEditText: EditText, dialog: AlertDialog
    ) {
        var error = 0
        if (nameEditText.isEmptyInputData(getString(R.string.add_name))) error += 1
        if (priceEditText.isEmptyInputData(getString(R.string.add_price))) error += 1
        if (costPriceEdiText.isEmptyInputData(getString(R.string.add_cost_price))) error += 1
        if (numberEditText.isEmptyInputData(getString(R.string.add_incoming_number))) error += 1

        if (error > 0) return

        addItem(nameEditText, priceEditText, costPriceEdiText, numberEditText, dialog)
    }

    private fun addItem(
        nameEditText: EditText, priceEditText: EditText,
        costPriceEdiText: EditText, numberEditText: EditText, dialog: AlertDialog
    ) {
        val product = Product(
            nameEditText.text.toString(),
            priceEditText.text.toString().toInt(),
            costPriceEdiText.text.toString().toInt(),
            numberEditText.text.toString().toInt(),
            numberEditText.text.toString().toInt()
        )

        adapter.addItem(product)
        dialog.dismiss()
    }

    private fun deleteSwipeAction() {
        val swipeHandler = object : ItemSimpleTouch(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
//                val item = viewModel.products?.get(position)
                adapter.deleteItem(position)

//                showActionSnackbar(rv_product_list, "Вы удалили товар", "Востановить",
//                    { restoreDeletedItem(item, position)}, requireContext())
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(rv_product_list)
    }

    private fun restoreDeletedItem(item: Product?, position: Int) {
        adapter.restoreItem(item, position)
    }

    override fun onItemClick(item: Product) {
        val intent = Intent(requireContext(), DetailProductActivity::class.java)
        intent.putExtra(product_detail, item)
        startActivity(intent)
    }

    override fun onLongItemClick(item: Product) {
        showSellingAlertDialog(item)
    }

    private fun showSellingAlertDialog(item: Product) {
        val alert = AlertDialog.Builder(requireContext())
        val view: View = layoutInflater.inflate(R.layout.alert_sell, null)
        alert.setView(view)
            .setCustomTitle(title_dialog)
            .setCancelable(false)
        alert.setPositiveButton("ДА"){ dialog, which ->
            val intent = Intent(requireContext(), SellProductActivity::class.java)
            intent.putExtra(product_detail, item)
            startActivity(intent)
            dialog.dismiss()
        }
        alert.setNegativeButton("НЕТ"){ dialog, which ->
            dialog.dismiss()
        }
        alert.show()
    }

    companion object {
        val product_detail = "PRODUCT_DETAIL"
    }
}