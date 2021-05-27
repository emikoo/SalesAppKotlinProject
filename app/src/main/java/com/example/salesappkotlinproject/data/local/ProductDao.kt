package com.example.salesappkotlinproject.data.local

import androidx.room.*
import com.example.salesappkotlinproject.data.model.Product
import com.example.salesappkotlinproject.data.model.SoldProduct

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(data: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun restoreProduct(data: Product)

    @Update
    fun updateItem(data: Product)

    @Query("SELECT * FROM product")
    fun getProducts(): MutableList<Product>

    @Delete
    fun deleteProduct(data: Product)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSoldProduct(data: SoldProduct)

    @Query("SELECT * FROM sold_product")
    fun getSoldProducts(): MutableList<SoldProduct>
}