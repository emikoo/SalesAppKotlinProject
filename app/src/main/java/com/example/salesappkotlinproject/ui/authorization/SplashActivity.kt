package com.example.salesappkotlinproject.ui.authorization

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.salesappkotlinproject.R
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        setupListener()
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