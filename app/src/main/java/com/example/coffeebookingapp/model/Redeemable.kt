package com.example.coffeebookingapp.model

data class Redeemable(
    val id: String,
    val product: String,
    val validUntil: String,
    val pointsRequired: Int
)