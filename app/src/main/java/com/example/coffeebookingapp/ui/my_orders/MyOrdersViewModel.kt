package com.example.coffeebookingapp.ui.my_orders

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.example.coffeebookingapp.data.MainRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class Tab(val title: String) {
    ONGOING("Ongoing"),
    HISTORY("History"),
}

class MyOrdersViewModel(
    resetTab: Boolean,
    private val repository: MainRepository,
    savedStateHandle: SavedStateHandle
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

    @OptIn(SavedStateHandleSaveableApi::class)
    var currentTab by savedStateHandle.saveable {
        mutableStateOf(Tab.values()[0])
    }
        private set

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

    fun setTab(tab: Tab) {
        currentTab = tab
    }

    init {
        if (resetTab) {
            currentTab = Tab.values()[0]
        }
    }

    companion object {
        fun provideFactory(
            resetTab: Boolean = false,
            repository: MainRepository,
        ): ViewModelProvider.Factory = object : AbstractSavedStateViewModelFactory() {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                key: String,
                modelClass: Class<T>,
                handle: SavedStateHandle
            ): T {
                return MyOrdersViewModel(resetTab, repository, handle) as T
            }
        }
    }
}