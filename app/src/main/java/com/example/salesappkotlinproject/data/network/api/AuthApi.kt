package com.example.salesappkotlinproject.data.network.api

import com.example.salesappkotlinproject.data.model.TokenModel
import com.example.salesappkotlinproject.data.model.User
import com.example.salesappkotlinproject.helper.ApiConstants.LOGIN_TOKEN_URL
import com.example.salesappkotlinproject.helper.ApiConstants.REFRESH_TOKEN_URL
import com.example.salesappkotlinproject.helper.ApiConstants.REGISTER_URL
import com.example.salesappkotlinproject.helper.PrefsHelper
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {
    @POST(REGISTER_URL)
    fun regUser(@Body data: User): Call<User>

    @POST(LOGIN_TOKEN_URL)
    fun login(@Body data: Map<String, String>): Call<TokenModel>

    @POST(REFRESH_TOKEN_URL)
    fun refreshToken(@Body data: TokenModel): Call<TokenModel>
}