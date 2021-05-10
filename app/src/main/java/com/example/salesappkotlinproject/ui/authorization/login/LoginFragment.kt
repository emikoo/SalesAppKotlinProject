package com.example.salesappkotlinproject.ui.authorization.login

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.salesappkotlinproject.R
import com.example.salesappkotlinproject.ui.authorization.registration.RegistrationFragment
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login_btn_registration.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        setupListener()
    }

    private fun setupListener() {
        login_btn_registration.setOnClickListener {
            childFragmentManager.beginTransaction().replace(R.id.login_card_view, RegistrationFragment()).commit()
        }

        login_btn_then.setOnClickListener {
            childFragmentManager.beginTransaction().replace(R.id.login_card_view, PersonFragment()).commit()
        }
    }
}