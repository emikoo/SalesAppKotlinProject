package com.example.salesappkotlinproject.view_model

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
        data?.let { repository.insertProduct(data) }
    }

    fun deleteProduct(data: Product?) {
        data?.let { repository.deleteProduct(it) }
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