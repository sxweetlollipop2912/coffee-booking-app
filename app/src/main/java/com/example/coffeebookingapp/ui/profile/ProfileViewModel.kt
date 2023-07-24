package com.example.coffeebookingapp.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.coffeebookingapp.data.MainRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: MainRepository
) : ViewModel() {
    val fullName = repository.observeFullName().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        "---"
    )
    val phone = repository.observePhone().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        "---"
    )
    val email = repository.observeEmail().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        "---"
    )
    val address = repository.observeAddress().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        "---"
    )

    fun changeFullName(string: String) {
        viewModelScope.launch {
            repository.changeFullName(string)
        }
    }

    fun changePhone(string: String): Boolean {
        viewModelScope.run {
            return repository.changePhone(string)
        }
    }

    fun changeEmail(string: String): Boolean {
        viewModelScope.run {
            return repository.changeEmail(string)
        }
    }

    fun changeAddress(string: String) {
        viewModelScope.launch {
            repository.changeAddress(string)
        }
    }

    companion object {
        fun provideFactory(
            repository: MainRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ProfileViewModel(repository) as T
            }
        }
    }
}