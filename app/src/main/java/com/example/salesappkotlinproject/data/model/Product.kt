package com.example.salesappkotlinproject.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "product")
data class Product (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var salePrice: Int,
    var costPrice: Int,
    var date: Date,
    var count: Int,
    var availableCount: Int = 0,
    var sold: Boolean = false,
    var countSold: Int = 0,
    var soldDate: Date? = null
) : Serializable
