package com.example.salesappkotlinproject.ui.owner.bottom_nav.sales_history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.salesappkotlinproject.data.model.Product
import com.example.salesappkotlinproject.repository.ProductRepository
import com.example.salesappkotlinproject.repository.ProductRepositoryImpl

class SoldProductViewModel(private val repository: ProductRepositoryImpl) : ViewModel() {

    val data: MutableLiveData<MutableList<Product>> = MutableLiveData()
    val message: MutableLiveData<String>? = MutableLiveData()
    var products: MutableList<Product>? = mutableListOf()
    var product: MutableList<Product> = mutableListOf()

    init {
        subscribeToData()
        subscribeToMessage()
        getSoldProducts()
    }

    fun getSoldProducts() {
        repository.getSoldProducts()
    }

    private fun subscribeToData() {
        repository.data.observeForever {
            products = data.value
            data.value = it
            product = it
        }
    }

    private fun subscribeToMessage() {
        repository.message?.observeForever {
            message?.value = it
        }
    }
}