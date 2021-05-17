package com.example.salesappkotlinproject.repository

import androidx.lifecycle.MutableLiveData
import com.example.salesappkotlinproject.data.model.TokenModel
import com.example.salesappkotlinproject.data.model.User
import com.example.salesappkotlinproject.data.network.api.AuthApi
import com.example.salesappkotlinproject.data.network.client.ResponseResult
import com.example.salesappkotlinproject.helper.PrefsHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface UserRepository {
    fun regUser(user: User): MutableLiveData<ResponseResult<User>>
    fun login(username: String, password: String): MutableLiveData<ResponseResult<TokenModel>>
}

class UserRepositoryImpl(private val api: AuthApi) : UserRepository {
    override fun regUser(user: User): MutableLiveData<ResponseResult<User>> {
        val data: MutableLiveData<ResponseResult<User>> = MutableLiveData(ResponseResult.loading())
        api.regUser(user).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                data.value = ResponseResult.error(t.message)
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                data.value = ResponseResult.success(response.body())
            }
        })
        return data
    }

    override fun login(username: String, password: String): MutableLiveData<ResponseResult<TokenModel>> {
        val map = mapOf(
            "username" to username,
            "password" to password
        )
        val data: MutableLiveData<ResponseResult<TokenModel>> = MutableLiveData(ResponseResult.loading())
        api.login(map).enqueue(object : Callback<TokenModel> {
            override fun onFailure(call: Call<TokenModel>, t: Throwable) {
                data.value = ResponseResult.error(t.message)
            }

            override fun onResponse(call: Call<TokenModel>, response: Response<TokenModel>) {
                when (response.code()) {
                    200 -> ResponseResult.success(response.body())
                    401 -> ResponseResult.error("No active account found with the given credentials")
                }
            }

        })
        return data
    }


}
