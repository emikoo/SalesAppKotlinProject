package com.example.salesappkotlinproject.ui.authorization.login

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.salesappkotlinproject.R
import com.example.salesappkotlinproject.ui.authorization.registration.AuthViewModel
import com.example.salesappkotlinproject.ui.authorization.registration.RegistrationFragment
import com.example.salesappkotlinproject.ui.owner.main.MainActivity
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class LoginFragment : Fragment() {

    lateinit var viewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel(clazz = AuthViewModel::class)
        login_btn_registration.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        setupListener()
        setupViewModel()
    }

    private fun setupListener() {
        login_btn_registration.setOnClickListener {
            childFragmentManager.beginTransaction().replace(R.id.login_card_view, RegistrationFragment()).commit()
        }

        login_btn_then.setOnClickListener {
            viewModel.login(
                username = login_et_login.text.toString(),
                password = login_et_password.text.toString()
            )
        }
    }

    private fun setupViewModel() {
        viewModel.actionNewScreen.observe(requireActivity(), Observer{
            startActivity(Intent(requireContext(), MainActivity::class.java))
        })

        viewModel.error.observe(requireActivity(), Observer{
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }
}