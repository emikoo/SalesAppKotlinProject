package com.example.salesappkotlinproject.ui.owner.bottom_nav.sales_history

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
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

        btn_reset.setOnClickListener {
            showAlertReset()
        }
    }

    private fun setupRecyclerView() {
        adapter =
            SalesHistoryAdapter()
        val layoutManager: LinearLayoutManager = LinearLayoutManager(activity)
        layoutManager.setReverseLayout(true)
        layoutManager.setStackFromEnd(true);
        rv_sales_list.setLayoutManager(layoutManager)
        rv_sales_list.adapter = adapter
    }


    private fun showAlertReset() {
        val alert = AlertDialog.Builder(requireContext(), R.style.ProductDialogStyle)

        val inflater = layoutInflater.inflate(R.layout.alert_sell, null)
        alert.setView(inflater)
        val negativeButton: Button = inflater.findViewById(R.id.negative_button)
        val positiveButton: Button = inflater.findViewById(R.id.positive_button)

        val dialog = alert.create()

        negativeButton.setOnClickListener {
            dialog.dismiss()
        }
        positiveButton.setOnClickListener {
            viewModel.deleteSoldProductsList()
            viewModel.getSoldProducts()
            tv_total_som.text = "0"
            tv_sales_sht.text = "0".toSht()
            tv_proceed_som.text = "0".toSom()
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun subscribeToLiveData() {
        viewModel.data.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            adapter.addItems(it)
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getSoldProducts()
        makeAnalytics()
    }

    private fun makeAnalytics() {
        val productsArray = viewModel.soldProduct
        var soldNumber = 0
        var total = 0
        var costPrice = 0
        var proceed = 0

        for (item in productsArray) {
            var numTotal = 0
            var numCostPrice = 0

            numTotal += item.salePrice * item.countSold
            numCostPrice += item.costPrice * item.countSold
            soldNumber += item.countSold

            total += numTotal
            costPrice += numCostPrice

            proceed = total - costPrice

            tv_total_som.text = total.toString()
            tv_sales_sht.text = soldNumber.toString().toSht()
            tv_proceed_som.text = proceed.toString().toSom()
        }
    }


}