package com.example.coffeebookingapp.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.coffeebookingapp.data.MainRepository
import com.example.coffeebookingapp.model.CartItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class CartViewModel(
    private val repository: MainRepository
): ViewModel() {
    val items = repository.observeCart().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    private val _totalPrice = MutableStateFlow<Int>(0)
    val totalPrice = _totalPrice.asStateFlow()

    fun priceOf(itemId: String): Int {
        val item = items.value.find { it.id == itemId } ?: return 0
        return repository.getPrice(item.product, item.option)
    }

    fun removeFromCart(itemId: String) {
        val item = items.value.find { it.id == itemId } ?: return
        val price = repository.getPrice(item.product, item.option)
        viewModelScope.launch {
            repository.removeFromCart(itemId)
            _totalPrice.update { it - price }
        }
    }

    companion object {
        fun provideFactory(
            repository: MainRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CartViewModel(repository) as T
            }
        }
    }
}