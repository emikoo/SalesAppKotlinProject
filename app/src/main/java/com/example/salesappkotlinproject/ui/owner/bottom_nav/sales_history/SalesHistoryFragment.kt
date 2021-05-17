package com.example.salesappkotlinproject.ui.owner.bottom_nav.sales_history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.salesappkotlinproject.R
import com.example.salesappkotlinproject.helper.toSht
import com.example.salesappkotlinproject.helper.toSom
import kotlinx.android.synthetic.main.fragment_sales_history.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

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

        viewModel = getViewModel(clazz = SoldProductViewModel::class)
        setupViews()
        subscribeToLiveData()
    }

    private fun setupViews() {
        setupRecyclerView()
        makeAnalytics()
    }

    private fun setupRecyclerView() {
        adapter =
            SalesHistoryAdapter()
        rv_sales_list.layoutManager = LinearLayoutManager(requireContext())
        rv_sales_list.adapter = adapter
    }

    private fun makeAnalytics() {
        val productsArray = viewModel.product
        var soldNumber = 0
        var proceed = 0
        var total = 0
        for (array in productsArray) {
            var numTotal = 0
            numTotal = array.countSold * array.salePrice
            total += numTotal

            soldNumber += array.countSold

            var numProceed = 0
            numProceed = array.countSold * array.costPrice
            proceed += numProceed

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