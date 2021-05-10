package com.example.salesappkotlinproject.data.network

import com.example.salesappkotlinproject.data.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("auth/users/")
    fun regUser(@Body data: User): Call<User>
}