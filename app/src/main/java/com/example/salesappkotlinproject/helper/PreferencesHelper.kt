package com.example.salesappkotlinproject.helper

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {

    private const val PREFERENCE_NAME = "UserTokenPreference"
    private const val NAME = "NAME"
    private const val PASSWORD = "PASSWORD"

    private var preference: SharedPreferences? = null

    fun init(context: Context) {
        preference = context.getSharedPreferences(
            PREFERENCE_NAME, Context.MODE_PRIVATE
        )
    }

    fun setName(data: String) {
        preference?.edit()?.putString(
            NAME, data
        )?.apply()
    }

    fun getName(): String? {
        return preference?.getString(
            NAME, null
        )
    }

    fun setPassword(data: String) {
        preference?.edit()?.putString(
            PASSWORD, data
        )?.apply()
    }

    fun getPassword(): String? {
        return preference?.getString(
            PASSWORD, null
        )
    }
}