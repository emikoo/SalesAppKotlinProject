package com.example.salesappkotlinproject.repository

import androidx.lifecycle.MutableLiveData
import com.example.salesappkotlinproject.data.local.ProductDao
import com.example.salesappkotlinproject.data.model.SoldProduct

interface SoldProductRepository {
    fun insertSoldProduct(data: SoldProduct)
    fun getSoldProduct()
}

class SoldProductRepositoryImpl(private val database: ProductDao): SoldProductRepository {

    val data: MutableLiveData<MutableList<SoldProduct>> = MutableLiveData()
    val message: MutableLiveData<String>? = MutableLiveData()

    override fun insertSoldProduct(data: SoldProduct) {
        database.insertSoldProduct(data)
    }

    override fun getSoldProduct() {
        data.value = database.getSoldProducts()
    }

}

