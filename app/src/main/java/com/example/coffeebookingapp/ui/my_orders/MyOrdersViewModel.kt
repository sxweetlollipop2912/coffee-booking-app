package com.example.coffeebookingapp.ui.my_orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.coffeebookingapp.data.MainRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class MyOrdersViewModel(
    private val repository: MainRepository
) : ViewModel() {
    val ongoing = repository.observeOngoingOrders().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
    val history = repository.observeHistoryOrders().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun moveToHistory(orderId: String) {
        viewModelScope.launch {
            repository.moveToHistory(orderId)
        }
    }

    fun moveToOngoing(orderId: String) {
        viewModelScope.launch {
            repository.moveToOngoing(orderId)
        }
    }

    companion object {
        fun provideFactory(
            repository: MainRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MyOrdersViewModel(repository) as T
            }
        }
    }
}