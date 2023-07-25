package com.example.coffeebookingapp.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.coffeebookingapp.data.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

data class UIState(
    val checkOutSucceeded: Boolean = false
)

class CartViewModel(
    private val repository: MainRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UIState())
    val uiState = _uiState.asStateFlow()

    val items = repository.observeCart().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun removeFromCart(itemId: String): Boolean {
        viewModelScope.run {
            return repository.removeFromCart(itemId)
        }
    }

    fun checkOut(): Boolean {
        viewModelScope.run {
            if (repository.checkOut()) {
                _uiState.update { UIState(checkOutSucceeded = true) }
                return true
            }
        }
        return false
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