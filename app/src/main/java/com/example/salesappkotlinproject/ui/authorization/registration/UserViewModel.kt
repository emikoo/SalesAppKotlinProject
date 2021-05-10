package com.example.salesappkotlinproject.ui.authorization.registration

import androidx.lifecycle.MutableLiveData
import com.example.salesappkotlinproject.data.common.BaseViewModel
import com.example.salesappkotlinproject.data.model.User
import com.example.salesappkotlinproject.repository.UserRepositoryImpl

class UserViewModel: BaseViewModel() {
    private val repository = UserRepositoryImpl()

    init {
        message = MutableLiveData()
    }

    fun regUser(user: User){
        repository.regUser(user)
    }
}