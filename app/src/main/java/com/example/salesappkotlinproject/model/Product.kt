package com.example.salesappkotlinproject.model

import java.io.Serializable
import java.util.*

data class Product (
    var name: String,
    var sale_price: Int,
    var cost_price: Int,
    var count: Int,
    var available_count: Int = 0,
    var count_sold: Int = 0,
    var date: Date? = null,
    var owner: Int? = null
) : Serializable