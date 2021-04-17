package com.example.salesappkotlinproject.ui.product_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.salesappkotlinproject.R
import com.example.salesappkotlinproject.ui.product_list.adapter.ProductListAdapter
import kotlinx.android.synthetic.main.fragment_product_list.*

class ProductListFragment : Fragment() {

    private lateinit var adapter: ProductListAdapter

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
    }

    private fun setupRecyclerView() {
        adapter = ProductListAdapter()
        rv_product_list.layoutManager = LinearLayoutManager(requireContext())
        rv_product_list.adapter = adapter
    }
}