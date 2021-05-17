package com.example.salesappkotlinproject.ui.authorization.registration

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.salesappkotlinproject.R
import com.example.salesappkotlinproject.data.model.User
import com.example.salesappkotlinproject.helper.showToast
import com.example.salesappkotlinproject.ui.authorization.login.LoginFragment
import com.example.salesappkotlinproject.ui.owner.main.MainActivity
import kotlinx.android.synthetic.main.fragment_registration.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class RegistrationFragment : Fragment() {

    lateinit var view_model: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view_model = getViewModel(clazz = AuthViewModel::class)
        reg_btn_login.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        setupListener()
    }

    private fun setupListener() {
        reg_btn_login.setOnClickListener {
            childFragmentManager.beginTransaction().replace(R.id.reg_card_view, LoginFragment()).commit()
        }

        reg_btn_go.setOnClickListener {
            saveData()
 //           openOwnerScreen()
        }
    }

    private fun saveData() {
        val username = reg_et_login.text.toString()
        val password = reg_et_password.text.toString()
        if (username != null && password != null){
            val user = User(username, password, true)
            view_model.regUser(user)
        }
    }

    private fun openOwnerScreen() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
    }
}