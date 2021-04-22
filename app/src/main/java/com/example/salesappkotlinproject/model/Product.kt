package com.example.salesappkotlinproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "product")
data class Product (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var sale_price: Int,
    var cost_price: Int,
    var date: Date,
    var count: Int,
    var available_count: Int = 0,
    var sold: Boolean = false,
    var count_sold: Int = 0,
    var soldDate: Date? = null
) : Serializable
