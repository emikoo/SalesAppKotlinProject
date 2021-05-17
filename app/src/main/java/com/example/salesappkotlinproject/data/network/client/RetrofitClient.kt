package com.example.notesapp.data.network.client

import com.example.salesappkotlinproject.data.model.TokenModel
import com.example.salesappkotlinproject.data.network.api.AuthApi
import com.example.salesappkotlinproject.helper.ApiConstants.BASE_URL
import com.example.salesappkotlinproject.helper.PrefsHelper
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

fun provideUserApi(retrofit: Retrofit) = retrofit.create(AuthApi::class.java)

fun provideOkHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    tokenAuthenticator: TokenAuthenticator
): OkHttpClient {
    return OkHttpClient()
        .newBuilder()
        .addInterceptor(httpLoggingInterceptor)
        .authenticator(tokenAuthenticator)
        .build()
}

fun provideHttpLoginingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

class HeadersInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = PrefsHelper.getToken()
        val request = chain.request().newBuilder()
        if (token.isNotEmpty())
            request.addHeader("Authorization", "Bearer $token")

        return chain.proceed(request.build())
    }
}


//Проблемный класс с вызовом refreshToken
class TokenAuthenticator(private val authApi: AuthApi, private val prefs: PrefsHelper) : Authenticator {
    override fun authenticate(route: Route?, res: Response): Request? {
        if (res.code == 401) {
            authApi.refreshToken(TokenModel(refreshToken = prefs.getRefreshToken())).enqueue(object : Callback<TokenModel> {
                override fun onFailure(call: Call<TokenModel>, t: Throwable) {}

                override fun onResponse(call: Call<TokenModel>, response: retrofit2.Response<TokenModel>) {
                    if (response.isSuccessful && response.body() != null) {
                        val token = response.body()?.accessToken ?: ""
                        token.let { prefs.saveToken(it, prefs.getRefreshToken()) }
//                        res.request.newBuilder()
//                            .addHeader("Authorization", "Bearer $token")
//                            .build()
                    }
                }
            })
        }
        return null
    }
}