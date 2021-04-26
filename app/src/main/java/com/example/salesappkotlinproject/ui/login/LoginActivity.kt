package com.example.salesappkotlinproject.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.salesappkotlinproject.R
import com.example.salesappkotlinproject.helper.PreferenceHelper
import com.example.salesappkotlinproject.helper.showToast
import com.example.salesappkotlinproject.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    lateinit var sharedPreferences: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        sharedPreferences = PreferenceHelper
        sharedPreferences.init(this)

        setupListener()
        checkAuthorization()
     }

    private fun setupListener() {
        btn_login.setOnClickListener {
            login()
        }
        btn_sign.setOnClickListener {
            registration()
        }
    }

    private fun login() {
        val login = et_username.text.toString()
        val password = et_password.text.toString()

        val username = sharedPreferences.getName()

        Log.d("GET_NAME", "$username")

        if (login != sharedPreferences.getName()
            && password != sharedPreferences.getPassword()) showToast(this, "Неправильный логин или пароль")
        else goToMainActivity()
    }

    private fun registration() {
        val username = et_username.text.toString()
        val password = et_password.text.toString()

        sharedPreferences.setName(username)
        sharedPreferences.setPassword(password)
        goToMainActivity()
    }

    private fun checkAuthorization() {
        if (sharedPreferences.getName().isNullOrEmpty()
            && sharedPreferences.getPassword().isNullOrEmpty()) showToast(this, "Войдите в свой магазин")
        else goToMainActivity()
    }

    private fun goToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}