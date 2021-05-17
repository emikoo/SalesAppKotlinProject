package com.example.salesappkotlinproject.helper

import android.content.Context
import android.content.SharedPreferences

object PrefsHelper {

    private const val PREFS_NAME = "SalesApp"
    private const val TOKEN = "TOKEN"
    private const val REFRESH_TOKEN = "REFRESH_TOKEN"
    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveToken(token: String?, refreshToken: String?) {
        prefs.edit().putString(TOKEN, token).apply()
        prefs.edit().putString(REFRESH_TOKEN, refreshToken).apply()
    }

    fun getToken(): String {
        return prefs.getString(TOKEN, "") ?: ""
    }

    fun getRefreshToken(): String {
        return  prefs.getString(REFRESH_TOKEN, "") ?: ""
    }
}