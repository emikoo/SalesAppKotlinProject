package com.example.salesappkotlinproject.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

abstract class BaseFragment<VM : BaseViewModel>(
    private val layoutId: Int,
    private val clazz: KClass<VM>
): Fragment() {

    lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            viewModel = getViewModel(clazz = clazz)
            setupViews()
            subscribeToLiveData()
    }

    abstract fun setupViews()
    abstract fun subscribeToLiveData()
}