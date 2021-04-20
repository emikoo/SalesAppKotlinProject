package com.example.salesappkotlinproject.ui.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.salesappkotlinproject.R
import com.example.salesappkotlinproject.helper.PreferenceHelper
import com.example.salesappkotlinproject.helper.SessionManager
import com.example.salesappkotlinproject.helper.showToast
import com.example.salesappkotlinproject.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sessionManager = SessionManager(this)

        setupListener()
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

        val token = "Some token From Server"
        val preferences: SharedPreferences =
            this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE)
        preferences.edit().putString("TOKEN", token).apply()

        val loginFromPref = PreferenceHelper.getName().toString()
        val passwordFromPref = PreferenceHelper.getPassword().toString()

        if (loginFromPref == login && passwordFromPref == password) {
            goToMainActivity()
        } else showToast(this, "Неверный логин или пароль")
    }

    private fun registration() {
        val username = et_username.text.toString()
        val password = et_password.text.toString()
    }

    private fun goToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}