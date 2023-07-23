package com.example.coffeebookingapp.ui.rewards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.coffeebookingapp.data.MainRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RewardsViewModel(
    private val repository: MainRepository
) : ViewModel() {
    val stampCount = repository.observeStampCount().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        0
    )
    val points = repository.observePoints().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        0
    )
    val history = repository.observePointsHistory().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

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
                return RewardsViewModel(repository) as T
            }
        }
    }
}