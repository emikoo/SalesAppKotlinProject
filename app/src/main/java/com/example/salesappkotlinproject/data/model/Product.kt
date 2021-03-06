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
    var count: Int,
    var availableCount: Int = 0,
    var countSold: Int = 0
) : Serializable