package com.example.salesappkotlinproject.ui.bottom_nav.product_list.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.salesappkotlinproject.model.Product
import com.example.salesappkotlinproject.repository.ProductRepository

class ProductViewModel : ViewModel() {
    private val repository = ProductRepository()

    val data: MutableLiveData<MutableList<Product>> = MutableLiveData()
    val message: MutableLiveData<String>? = MutableLiveData()
    var products: MutableList<Product>? = mutableListOf()

    init {
        subscribeToData()
        subscribeToMessage()
        repository.getProducts()
    }

    fun insertProduct(data: Product?) {
        if (data != null) {
            repository.insertProduct(data)
            products?.add(data)
        }
    }

    fun restoreProduct(data: Product?) {
        if (data != null) {
            repository.restoreProduct(data)
        }
    }

    fun deleteProduct(data: Product?) {
        if (data != null) {
            repository.deleteProduct(data)
        }
    }

    private fun subscribeToData() {
        repository.data.observeForever {
            data.value = it
            products = data.value
        }
    }

    private fun subscribeToMessage() {
        repository.message?.observeForever {
            message?.value = it
        }
    }
}