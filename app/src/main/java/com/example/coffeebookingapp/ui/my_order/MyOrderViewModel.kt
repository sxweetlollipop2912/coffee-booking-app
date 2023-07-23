package com.example.coffeebookingapp.ui.my_order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.coffeebookingapp.data.MainRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class MyOrderViewModel(
    private val repository: MainRepository
) : ViewModel() {
    val ongoing = repository.observeOngoingOrders().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptySet()
    )
    val history = repository.observeHistoryOrders().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptySet()
    )

    fun moveToHistory(orderID: String) {
        viewModelScope.launch {
            repository.moveToHistory(orderID)
        }
    }

    fun moveToOngoing(orderID: String) {
        viewModelScope.launch {
            repository.moveToOngoing(orderID)
        }
    }

    companion object {
        fun provideFactory(
            repository: MainRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MyOrderViewModel(repository) as T
            }
        }
    }
}