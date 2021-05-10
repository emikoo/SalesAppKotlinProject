package com.example.salesappkotlinproject.ui.authorization.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.salesappkotlinproject.R
import com.example.salesappkotlinproject.ui.owner.main.MainActivity
import kotlinx.android.synthetic.main.fragment_person.*

class PersonFragment : Fragment() {
     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_person, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListener()
    }

    private fun setupListener() {
        person_btn_back.setOnClickListener {
            childFragmentManager.beginTransaction().replace(R.id.person_card_view, LoginFragment()).commit()
        }

        btn_owner.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
    }
}