package com.example.salesappkotlinproject.ui.authorization

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.salesappkotlinproject.R
import com.example.salesappkotlinproject.helper.PrefsHelper
import com.example.salesappkotlinproject.ui.owner.main.MainOwnerActivity
import kotlinx.android.synthetic.main.activity_main_authorization.*


class MainAuthorizationActivity : AppCompatActivity() {

    lateinit var prefs: PrefsHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_authorization)

        prefs = PrefsHelper(this)

        checkIsLogged()
        setupListener()
     }

    private fun checkIsLogged() {
        if (prefs.getToken() != "") {
            startActivity(Intent(this, MainOwnerActivity::class.java))
            finish()
        }
    }

    private fun setupListener() {
        btn_login.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        btn_sign_up.setOnClickListener {
            val intent = Intent(this, AuthorizationActivity::class.java)
            intent.putExtra(auth_tag, 1)
            startActivity(intent)
        }

        btn_login.setOnClickListener {
            val intent = Intent(this, AuthorizationActivity::class.java)
            intent.putExtra(auth_tag, 2)
            startActivity(intent)
        }
    }

    companion object {
        const val auth_tag = "AUTHORIZATION"
    }
}