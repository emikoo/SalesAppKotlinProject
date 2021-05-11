package com.example.salesappkotlinproject.app

import android.app.Application
import androidx.room.Room
import com.example.salesappkotlinproject.data.local.AppDatabase

class ApplicationEnactus : Application() {
    companion object{
        lateinit var db: AppDatabase
        fun getAppDatabase(): AppDatabase =
            db
    }

    override fun onCreate() {
        super.onCreate()
        db = Room
            .databaseBuilder(applicationContext, AppDatabase::class.java, "product_db")
            .allowMainThreadQueries()
            .build()
    }
}