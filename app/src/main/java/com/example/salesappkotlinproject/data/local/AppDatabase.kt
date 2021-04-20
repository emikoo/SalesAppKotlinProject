package com.example.salesappkotlinproject.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.salesappkotlinproject.helper.Converters
import com.example.salesappkotlinproject.model.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao() : ProductDao
}