package com.example.salesappkotlinproject.ui.bottom_nav.sales_history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.salesappkotlinproject.R
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
        setupRecyclerView()
        subscribeToLiveData()
    }

    private fun setupRecyclerView() {
        adapter = SalesHistoryAdapter()
        rv_sales_list.layoutManager = LinearLayoutManager(requireContext())
        rv_sales_list.adapter = adapter
    }

    private fun subscribeToLiveData() {
        viewModel.data.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            adapter.addItems(it)
        })
    }
}