package com.example.salesappkotlinproject.data.network.api

import com.example.salesappkotlinproject.data.model.TokenModel
import com.example.salesappkotlinproject.data.model.AuthModel
import com.example.salesappkotlinproject.helper.Constants.CREATE_TOKEN_URL
import com.example.salesappkotlinproject.helper.Constants.REGISTER_URL
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST(REGISTER_URL)
    fun regUser(@Body data: AuthModel): Call<AuthModel>

    @POST(CREATE_TOKEN_URL)
    suspend fun createAuthToken(@Body data: Map<String, String>): Call<TokenModel>
}