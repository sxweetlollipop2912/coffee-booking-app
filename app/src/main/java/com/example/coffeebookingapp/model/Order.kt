package com.example.coffeebookingapp.model

data class Order(
    val id: String,
    val product: String,
    val datetime: String,
    val price: Double,
    val address: String
)