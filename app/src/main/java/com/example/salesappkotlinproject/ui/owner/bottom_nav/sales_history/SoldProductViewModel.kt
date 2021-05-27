package com.example.salesappkotlinproject.ui.owner.bottom_nav.sales_history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.salesappkotlinproject.data.base.BaseEvent
import com.example.salesappkotlinproject.data.base.BaseViewModel
import com.example.salesappkotlinproject.data.model.Product
import com.example.salesappkotlinproject.data.model.SoldProduct
import com.example.salesappkotlinproject.repository.ProductRepository
import com.example.salesappkotlinproject.repository.ProductRepositoryImpl
import com.example.salesappkotlinproject.repository.SoldProductRepositoryImpl

class SoldProductViewModel(private val repository: SoldProductRepositoryImpl) : ViewModel() {

    val data: MutableLiveData<MutableList<SoldProduct>> = MutableLiveData()
    val message: MutableLiveData<String>? = MutableLiveData()
    var soldProduct: MutableList<SoldProduct> = mutableListOf()

    init {
        subscribeToData()
        subscribeToMessage()
        getSoldProducts()
    }

    fun getSoldProducts() {
        repository.getSoldProduct()
    }

    fun insertSoldProduct(data: SoldProduct) {
        repository.insertSoldProduct(data)
    }

    private fun subscribeToData() {
        repository.data.observeForever {
            it?.let { result -> data.value = result}
            data.value?.let { data -> soldProduct = data }
            data.value = it
        }
    }

    private fun subscribeToMessage() {
        repository.message?.observeForever {
            message?.value = it
        }
    }
}