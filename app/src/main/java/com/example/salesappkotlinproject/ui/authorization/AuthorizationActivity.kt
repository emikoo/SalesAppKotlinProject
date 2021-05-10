package com.example.salesappkotlinproject.ui.authorization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.salesappkotlinproject.R
import com.example.salesappkotlinproject.ui.authorization.SplashActivity.Companion.auth_tag
import com.example.salesappkotlinproject.ui.authorization.login.LoginFragment
import com.example.salesappkotlinproject.ui.authorization.registration.CheckOwnerFragment
import com.example.salesappkotlinproject.ui.authorization.registration.RegistrationFragment
import kotlinx.android.synthetic.main.activity_authorization.*

class AuthorizationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)

        val item = intent.getIntExtra(auth_tag, 0)
        if (item == 1) supportFragmentManager.beginTransaction().replace(R.id.auth_container, CheckOwnerFragment()).commit()
        else if (item == 2) supportFragmentManager.beginTransaction().replace(R.id.auth_container, LoginFragment()).commit()
    }
}