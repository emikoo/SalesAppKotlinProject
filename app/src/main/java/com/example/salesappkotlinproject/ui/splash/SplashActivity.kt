package com.example.salesappkotlinproject.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import com.example.salesappkotlinproject.R
import com.example.salesappkotlinproject.ui.owner.main.MainOwnerActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        setupAnimationText()
        setDelay()
    }

    private fun setupAnimationText() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        splash_activity.startAnimation(animation)
    }

    private fun setDelay() {
        Handler(Looper.getMainLooper()).postDelayed({
            openActivity()
        }, 1000)
    }

    private fun openActivity() {
        val intent = Intent(this, MainOwnerActivity::class.java)
        startActivity(intent)
        finish()
    }
}