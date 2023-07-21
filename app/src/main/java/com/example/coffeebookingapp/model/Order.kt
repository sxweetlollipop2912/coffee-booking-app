package com.example.coffeebookingapp.model

data class Order(
    val id: String,
    val product: String,
    val datetime: String,
    val price: String,
    val address: String
)