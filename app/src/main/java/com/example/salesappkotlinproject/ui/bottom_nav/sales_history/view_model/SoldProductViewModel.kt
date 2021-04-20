package com.example.salesappkotlinproject.ui.bottom_nav.sales_history.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.salesappkotlinproject.model.Product
import com.example.salesappkotlinproject.repository.ProductRepository

class SoldProductViewModel : ViewModel() {
    private val repository = ProductRepository()

    val data: MutableLiveData<MutableList<Product>> = MutableLiveData()
    val message: MutableLiveData<String>? = MutableLiveData()
    var products: MutableList<Product>? = mutableListOf()

    init {
        subscribeToData()
        subscribeToMessage()
        repository.getSoldProducts()
    }

    private fun subscribeToData() {
        repository.data.observeForever {
            products = data.value
            data.value = it
        }
    }

    private fun subscribeToMessage() {
        repository.message?.observeForever {
            message?.value = it
        }
    }
}