package com.example.coffeebookingapp.ui.redeem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.coffeebookingapp.data.MainRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class RedeemViewModel(
    private val repository: MainRepository
) : ViewModel() {
    val redeemable = repository.observeRedeemableProducts().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
    val points = repository.observePoints().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        0
    )

    fun checkIfRedeemable(redeemableId: String): Boolean {
        viewModelScope.run {
            return repository.checkIfRedeemable(redeemableId)
        }
    }

    companion object {
        fun provideFactory(
            repository: MainRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return RedeemViewModel(repository) as T
            }
        }
    }
}