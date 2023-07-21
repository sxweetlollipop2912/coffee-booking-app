package com.example.coffeebookingapp.model

data class CartItem(
    val id: String,
    val product: String,
    val price: String,
    val option: ProductOption
)