package com.example.salesappkotlinproject.repository

import androidx.lifecycle.MutableLiveData
import com.example.salesappkotlinproject.data.local.ApplicationEnactus
import com.example.salesappkotlinproject.model.Product

class ProductRepository {
    private val database =  ApplicationEnactus.getAppDatabase().productDao()

    val data: MutableLiveData<MutableList<Product>> = MutableLiveData()
    val message: MutableLiveData<String>? = MutableLiveData()

    fun getProducts() {
        data.value = database.getProducts()
    }

    fun insertProduct(data: Product) {
        database.insertProduct(data)
    }

    fun restoreProduct(data: Product) {
        database.restoreProduct(data)
    }

    fun deleteProduct(data: Product) {
        database.deleteProduct(data)
    }

    fun getSoldProducts(){
        data.value = database.getSoldProducts()
    }
}