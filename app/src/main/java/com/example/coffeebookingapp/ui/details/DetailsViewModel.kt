package com.example.coffeebookingapp.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coffeebookingapp.data.MainRepository
import com.example.coffeebookingapp.model.IceType
import com.example.coffeebookingapp.model.ProductOption
import com.example.coffeebookingapp.model.ShotType
import com.example.coffeebookingapp.model.SizeType
import com.example.coffeebookingapp.model.TemperatureType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DetailsViewModel(
    private val repository: MainRepository,
    val product: String
) : ViewModel() {
    private val _option = MutableStateFlow(
        ProductOption(
            quantity = 1,
            shot = ShotType.SINGLE,
            temperature = TemperatureType.ICED,
            size = SizeType.MEDIUM,
            ice = IceType.FULL
        )
    )
    val option = _option.asStateFlow()

    private val _totalPrice = MutableStateFlow(getPrice())
    val totalPrice = _totalPrice.asStateFlow()

    private fun getPrice(): Double {
        return repository.getPrice(product, option.value)
    }

    fun setQuantity(quantity: Int) {
        _option.update { it.copy(quantity = maxOf(quantity, 0)) }
        _totalPrice.update { getPrice() }
    }

    fun incQuantity() {
        _option.update { it.copy(quantity = it.quantity + 1) }
        _totalPrice.update { getPrice() }
    }

    fun decQuantity() {
        _option.update { it.copy(quantity = maxOf(it.quantity - 1, 0)) }
        _totalPrice.update { getPrice() }
    }

    fun setShot(shot: ShotType) {
        _option.update { it.copy(shot = shot) }
        _totalPrice.update { getPrice() }
    }

    fun setTemperature(temperature: TemperatureType) {
        _option.update { it.copy(temperature = temperature) }
        _totalPrice.update { getPrice() }
    }

    fun setSize(size: SizeType) {
        _option.update { it.copy(size = size) }
        _totalPrice.update { getPrice() }
    }

    fun setIce(ice: IceType) {
        _option.update { it.copy(ice = ice) }
        _totalPrice.update { getPrice() }
    }

    companion object {
        fun provideFactory(
            repository: MainRepository,
            product: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return DetailsViewModel(repository, product) as T
            }
        }
    }
}