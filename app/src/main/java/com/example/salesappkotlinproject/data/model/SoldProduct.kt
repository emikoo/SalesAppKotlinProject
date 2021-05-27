package com.example.salesappkotlinproject.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "sold_product")
data class SoldProduct(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    var salePrice: Int,
    var costPrice: Int,
    var countSold: Int,
    var totalSalePrice: Int? = null,
    var soldDate: String? =  null
    ): Serializable