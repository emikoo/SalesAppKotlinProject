package com.example.salesappkotlinproject.data.local

import android.app.Application
import androidx.room.Room

class ApplicationEnactus : Application() {
    companion object{
        private lateinit var db: AppDatabase
        fun getAppDatabase(): AppDatabase = db
    }

    override fun onCreate() {
        super.onCreate()
        db = Room
            .databaseBuilder(applicationContext, AppDatabase::class.java, "product_db")
            .allowMainThreadQueries()
            .build()
    }
}