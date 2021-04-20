package com.example.salesappkotlinproject.data.local

import androidx.room.*
import com.example.salesappkotlinproject.model.Product

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(data: Product)

    @Query("SELECT * FROM product")
    fun getProducts(): MutableList<Product>

    @Delete
    fun deleteProduct(data: Product)

    @Query("SELECT * FROM product WHERE sold == 1")
    fun getSoldProducts(): MutableList<Product>
}