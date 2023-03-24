package com.example.salesappkotlinproject.ui.owner.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.salesappkotlinproject.R
import com.example.salesappkotlinproject.ui.owner.bottom_nav.product_list.ProductListFragment
import com.example.salesappkotlinproject.ui.owner.bottom_nav.sales_history.SalesHistoryFragment
import com.example.salesappkotlinproject.ui.owner.main.adapter.MainViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainOwnerActivity : AppCompatActivity() {

    private lateinit var adapter: MainViewPagerAdapter

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewPager()
        setupBottomNavigation()
    }

    private fun setupViewPager() {
        adapter = MainViewPagerAdapter(this)
        adapter.addFragments(ProductListFragment())
        adapter.addFragments(SalesHistoryFragment())
        view_pager.adapter = adapter
        view_pager.isEnabled = false
        view_pager.isUserInputEnabled = false
    }

    private fun setupBottomNavigation() {
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.product_list -> {
                    view_pager.setCurrentItem(0, false)
                    main_title.text = "Склад"
                }
                R.id.sales_history -> {
                    view_pager.setCurrentItem(1, false)
                    main_title.text = "История продаж"
                }
            }
            true
        }
    }
}