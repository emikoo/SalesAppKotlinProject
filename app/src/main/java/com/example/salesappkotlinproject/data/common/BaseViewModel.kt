package com.example.salesappkotlinproject.data.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    var message = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
}