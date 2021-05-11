package com.example.salesappkotlinproject.data.model

import com.google.gson.annotations.SerializedName

data class TokenModel (
    @SerializedName("refresh")
    val refreshToken: String,
    @SerializedName("access")
    val accessToken: String,
    val role_code: String
)