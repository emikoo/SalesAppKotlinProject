package com.example.salesappkotlinproject.ui.authorization.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.salesappkotlinproject.data.common.BaseViewModel
import com.example.salesappkotlinproject.data.model.User
import com.example.salesappkotlinproject.data.network.client.ResponseResultStatus
import com.example.salesappkotlinproject.helper.PrefsHelper
import com.example.salesappkotlinproject.repository.UserRepository
import com.example.salesappkotlinproject.repository.UserRepositoryImpl
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: UserRepository) : BaseViewModel() {

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
                            PrefsHelper.saveToken(it?.result?.accessToken, it?.result?.refreshToken)
                        }
                        ResponseResultStatus.ERROR -> error.value = it.message
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