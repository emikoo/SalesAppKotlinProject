package com.example.salesappkotlinproject.model

import java.util.*

data class Product (
    var name: String,
    var price: Int? = null,
    var costPrice: Int? = null,
    var incomingNumber: Int? = null,
    var availableNumber: Int? = null,
    var soldNumber: Int? = null,
    var soldDate: Date? = null
)