package com.example.salesappkotlinproject.data.model

import com.google.gson.annotations.SerializedName

data class TokenModel (
    @SerializedName("refresh")
    val refreshToken: String? = null,
    @SerializedName("access")
    val accessToken: String? = null,
    val role_code: String? = null
)