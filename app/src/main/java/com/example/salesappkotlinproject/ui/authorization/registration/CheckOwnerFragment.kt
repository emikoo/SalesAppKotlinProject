package com.example.salesappkotlinproject.ui.authorization.registration

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.salesappkotlinproject.R
import com.example.salesappkotlinproject.helper.showToast
import com.example.salesappkotlinproject.ui.authorization.SplashActivity
import kotlinx.android.synthetic.main.fragment_check_owner.*

class CheckOwnerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_check_owner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListener()
    }

    private fun setupListener() {
        btn_owner.setOnClickListener {
            childFragmentManager.beginTransaction().replace(R.id.check_owner_card_view, RegistrationFragment()).commit()
        }

        btn_seller.setOnClickListener {
            showToast(requireContext(), "Только владельцы могут регистрироваться")
            val intent = Intent(requireContext(), SplashActivity::class.java)
            startActivity(intent)
        }
    }
}