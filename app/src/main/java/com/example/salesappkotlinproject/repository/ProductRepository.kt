package com.example.salesappkotlinproject.repository

import androidx.lifecycle.MutableLiveData
import com.example.salesappkotlinproject.data.local.ProductDao
import com.example.salesappkotlinproject.data.model.Product

interface ProductRepository{
    fun getProducts()
    fun insertProduct(data: Product)
    fun updateProduct(data: Product)
    fun restoreProduct(data: Product)
    fun deleteProduct(data: Product)
    fun getSoldProducts()
}

class ProductRepositoryImpl(private val database: ProductDao): ProductRepository {

    val data: MutableLiveData<MutableList<Product>> = MutableLiveData()
    val message: MutableLiveData<String>? = MutableLiveData()

    override fun getProducts() {
        data.value = database.getProducts()
    }

    override fun insertProduct(data: Product) {
        database.insertProduct(data)
    }

    override fun updateProduct(data: Product) {
        database.updateItem(data)
    }

    override fun restoreProduct(data: Product) {
        database.restoreProduct(data)
    }

    override fun deleteProduct(data: Product) {
        database.deleteProduct(data)
    }

    override fun getSoldProducts(){
        data.value = database.getSoldProducts()
    }
}