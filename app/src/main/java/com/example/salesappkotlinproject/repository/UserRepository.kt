package com.example.salesappkotlinproject.repository

import androidx.lifecycle.MutableLiveData
import com.example.notesapp.data.network.client.RetrofitClient
import com.example.salesappkotlinproject.data.model.User
import com.example.salesappkotlinproject.data.network.client.ResponseResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface UserRepository {
    fun regUser(user: User): MutableLiveData<ResponseResult<User>>
}

class UserRepositoryImpl: UserRepository {
    val api = RetrofitClient().userApi

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
}
