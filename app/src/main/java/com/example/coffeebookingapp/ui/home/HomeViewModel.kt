package com.example.coffeebookingapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.coffeebookingapp.data.MainRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: MainRepository
) : ViewModel() {
    val fullName = repository.observeFullName().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        "---"
    )
    val stampCount = repository.observeStampCount().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        0
    )
    val products = viewModelScope.run {
        return@run repository.getProducts()
    }

    fun resetStampCount() {
        viewModelScope.launch {
            repository.resetStampCount()
        }
    }

    companion object {
        fun provideFactory(
            repository: MainRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(repository) as T
            }
        }
    }
}