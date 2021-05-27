package com.example.salesappkotlinproject.data.network.api

import com.example.salesappkotlinproject.data.model.User
import com.example.salesappkotlinproject.helper.ApiConstants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ProductApi {

    @POST(ApiConstants.REGISTER_URL)
    fun regUser(@Body data: User): Call<User>
}