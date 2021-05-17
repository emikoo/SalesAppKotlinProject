package com.example.salesappkotlinproject.ui.owner.bottom_nav.product_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salesappkotlinproject.data.model.Product
import com.example.salesappkotlinproject.repository.ProductRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepositoryImpl) : ViewModel() {

    val data: MutableLiveData<MutableList<Product>> = MutableLiveData()
    val message: MutableLiveData<String>? = MutableLiveData()
    var products: MutableList<Product>? = mutableListOf()
    var filteredProducts: MutableList<Product> = mutableListOf()

    init {
        subscribeToData()
        subscribeToMessage()
        getProducts()
    }

    fun getProducts() {
        repository.getProducts()
    }

    fun insertProduct(data: Product?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (data != null) {
                repository.insertProduct(data)
            }
        }
    }

    fun restoreProduct(data: Product?) {
        if (data != null) {
            repository.restoreProduct(data)
        }
    }

    fun updateProduct(data: Product) {
        repository.updateProduct(data)
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
            filteredProducts = it
        }
    }

    private fun subscribeToMessage() {
        repository.message?.observeForever {
            message?.value = it
        }
    }
}