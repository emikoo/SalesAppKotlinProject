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
    fun refreshToken(): MutableLiveData<ResponseResult<String>>
}

class UserRepositoryImpl(private val api: AuthApi, private val preferences: PrefsHelper) : UserRepository {

    override fun refreshToken(): MutableLiveData<ResponseResult<String>> {
        val result = MutableLiveData<ResponseResult<String>>(ResponseResult.loading())
        api.refreshToken(TokenModel(refreshToken = preferences.getRefreshToken()))
            .enqueue(object : Callback<TokenModel> {
                override fun onFailure(call: Call<TokenModel>, t: Throwable) {}

                override fun onResponse(
                    call: Call<TokenModel>,
                    response: retrofit2.Response<TokenModel>
                ) {
                    if (response.isSuccessful && response.body() != null) {

                        val token = response.body()?.accessToken ?: ""
                        token.let { preferences.saveToken(it, preferences.getRefreshToken()) }
                        result.value = ResponseResult.success(preferences.getRefreshToken())
                    } else {
                        result.value = ResponseResult.error(response.message())
                    }
                }
            })
        return result
    }


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

    override fun login(
        username: String,
        password: String
    ): MutableLiveData<ResponseResult<TokenModel>> {
        val map = mapOf(
            "username" to username,
            "password" to password
        )
        val data: MutableLiveData<ResponseResult<TokenModel>> =
            MutableLiveData(ResponseResult.loading())
        api.login(map).enqueue(object : Callback<TokenModel> {
            override fun onFailure(call: Call<TokenModel>, t: Throwable) {
                data.value = ResponseResult.error(t.message)
            }

            override fun onResponse(call: Call<TokenModel>, response: Response<TokenModel>) {
                when (response.code()) {
                    200 -> data.value = ResponseResult.success(response.body())
                    401 -> data.value = ResponseResult.error("No active account found with the given credentials")
                }
            }

        })
        return data
    }


}
