package com.example.coffeebookingapp.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.coffeebookingapp.data.MainRepository
import com.example.coffeebookingapp.model.IceType
import com.example.coffeebookingapp.model.ProductOption
import com.example.coffeebookingapp.model.ShotType
import com.example.coffeebookingapp.model.SizeType
import com.example.coffeebookingapp.model.TemperatureType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val repository: MainRepository,
    val product: String,
    private val redeemableId: String?,
    private val cartId: String?
) : ViewModel() {
    private val _option = MutableStateFlow(
        viewModelScope.run {
            val cartOption = cartId?.let { repository.getCartItem(it) }?.option
            return@run ProductOption(
                quantity = cartOption?.quantity ?: 1,
                shot = cartOption?.shot ?: ShotType.SINGLE,
                temperature = cartOption?.temperature ?: TemperatureType.ICED,
                size = cartOption?.size ?: SizeType.MEDIUM,
                ice = cartOption?.ice ?: IceType.FULL
            )
        }
    )
    val option = _option.asStateFlow()

    val price: Double
        get() = viewModelScope.run {
            return repository.getPrice(product, option.value, redeemableId != null)
        }

    fun setQuantity(quantity: Int) {
        _option.update { it.copy(quantity = maxOf(quantity, 0)) }
    }

    fun incQuantity() {
        _option.update { it.copy(quantity = it.quantity + 1) }
    }

    fun decQuantity() {
        _option.update { it.copy(quantity = maxOf(it.quantity - 1, 0)) }
    }

    fun setShot(shot: ShotType) {
        _option.update { it.copy(shot = shot) }
    }

    fun setTemperature(temperature: TemperatureType) {
        _option.update { it.copy(temperature = temperature) }
    }

    fun setSize(size: SizeType) {
        _option.update { it.copy(size = size) }
    }

    fun setIce(ice: IceType) {
        _option.update { it.copy(ice = ice) }
    }

    fun addToCart() {
        viewModelScope.launch {
            if (cartId != null) {
                repository.modifyCartItem(
                    cartId,
                    option.value
                )
            } else {
                repository.addToCart(
                    product,
                    option.value,
                    redeemableId
                )
            }
        }
    }

    companion object {
        fun provideFactory(
            repository: MainRepository,
            product: String,
            redeemableId: String? = null,
            cartId: String? = null,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return DetailsViewModel(repository, product, redeemableId, cartId) as T
            }
        }
    }
}