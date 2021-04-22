package com.example.salesappkotlinproject.ui.bottom_nav.sales_history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.salesappkotlinproject.R
import com.example.salesappkotlinproject.helper.toSht
import com.example.salesappkotlinproject.helper.toSom
import com.example.salesappkotlinproject.model.Product
import com.example.salesappkotlinproject.ui.bottom_nav.sales_history.adapter.SalesHistoryAdapter
import com.example.salesappkotlinproject.ui.bottom_nav.product_list.view_model.ProductViewModel
import com.example.salesappkotlinproject.ui.bottom_nav.sales_history.view_model.SoldProductViewModel
import kotlinx.android.synthetic.main.fragment_sales_history.*

class SalesHistoryFragment : Fragment() {

    private lateinit var adapter: SalesHistoryAdapter
    private lateinit var viewModel: SoldProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sales_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(SoldProductViewModel::class.java)
        setupViews()
        subscribeToLiveData()
    }

    private fun setupViews() {
        setupRecyclerView()
        makeAnalytics()
    }

    private fun setupRecyclerView() {
        adapter = SalesHistoryAdapter()
        rv_sales_list.layoutManager = LinearLayoutManager(requireContext())
        rv_sales_list.adapter = adapter
    }

    private fun makeAnalytics() {
        val productsArray = viewModel.product
        var total = 0
        var soldNumber = 0
        var proceed = 0
        for (array in productsArray) {
            total += array.sale_price
            soldNumber += array.count_sold
            proceed += array.cost_price

            tv_total_som.text = total.toString()
            tv_sales_sht.text = soldNumber.toString().toSht()
            tv_proceed_som.text = proceed.toString().toSom()
        }
    }

    private fun subscribeToLiveData() {
        viewModel.data.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            adapter.addItems(it)
        })
    }
}