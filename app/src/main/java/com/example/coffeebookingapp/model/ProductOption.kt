package com.example.coffeebookingapp.model

enum class ShotType(val value: Int) {
    SINGLE(0),
    DOUBLE(1)
}

enum class TemperatureType(val value: Int) {
    HOT(0),
    COLD(1)
}

enum class SizeType(val value: Int) {
    SMALL(0),
    MEDIUM(1),
    LARGE(2)
}

enum class IceType(val value: Int) {
    LESS(0),
    NORMAL(1),
    MORE(2)
}

data class ProductOption(
    val quantity: Int,
    val shot: ShotType,
    val temperature: TemperatureType,
    val size: SizeType,
    val ice: IceType
)