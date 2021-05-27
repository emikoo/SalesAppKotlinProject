package com.example.salesappkotlinproject.ui.authorization

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.salesappkotlinproject.data.base.BaseViewModel
import com.example.salesappkotlinproject.data.model.User
import com.example.salesappkotlinproject.data.network.client.ResponseResultStatus
import com.example.salesappkotlinproject.helper.PrefsHelper
import com.example.salesappkotlinproject.repository.UserRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: UserRepository, private val preferences: PrefsHelper) : BaseViewModel() {

    val actionNewScreen = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    init {
        message = MutableLiveData()
    }

    fun regUser(user: User) {
        repository.regUser(user)
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            repository.login(username, password)
                .observeForever {
                    when (it.status) {
                        ResponseResultStatus.SUCCESS -> {
                            preferences.saveToken(it?.result?.accessToken, it?.result?.refreshToken)
                            actionNewScreen.value = true
                        }
                        ResponseResultStatus.ERROR ->{
                            error.value = it.message
                            actionNewScreen.value = false
                        }
                    }
                }
        }
    }
}

//{
//    "result" : {
//                "accessToken" : "fdjfklsdfjl",
//                "refreshToken" : "fdsjfklsdjflkjsdf" },
//    "status" : 200,
//    "details" : "Success authorizated"
//}
data class ResponseResult<T>(
    var result: T?,
    var status: String,
    var details: String
)