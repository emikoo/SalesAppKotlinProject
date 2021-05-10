package com.example.salesappkotlinproject.data.common

import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.example.salesappkotlinproject.R
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseActivity : AppCompatActivity() {



    protected fun createDrawer() {
        val header = createAccountHeader()
        val analytics = PrimaryDrawerItem().withName(R.string.analytics).withIdentifier(1)
            .withIcon(R.drawable.ic_calendar)

        DrawerBuilder()
            .withActivity(this)
            .withToolbar(main_toolbar)
            .withSelectedItem(-1)
            .withOnDrawerNavigationListener {
                onBackPressed()
                return@withOnDrawerNavigationListener true
            }
            .withAccountHeader(header)
            .addDrawerItems(
                analytics
            )
            .build()
    }

    private fun createAccountHeader(): AccountHeader {
        return AccountHeaderBuilder()
            .withActivity(this)
            .addProfiles(
                ProfileDrawerItem()
                    .withName("Koka Cosmetics")
                    .withIcon(R.drawable.ic_person)
                    .withEmail("Kanykei")
            )
            .withHeaderBackground(R.color.color_main_blue)
            .withAlternativeProfileHeaderSwitching(false)
            .withTranslucentStatusBar(true)
            .withAlternativeProfileHeaderSwitching(false)
            .build()

    }
}