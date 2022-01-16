package com.fusiontechph.sample.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fusiontechph.sample.data.rationale.RationaleRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    val rationaleRepository: RationaleRepository
) : ViewModel() {

    val showReadExternalStorageRationale = rationaleRepository.showReadExternalStorageRationale.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        true
    )

    init {
        viewModelScope.launch {
            showReadExternalStorageRationale.collect {  }
        }
    }

    fun disable() {
        viewModelScope.launch {
            rationaleRepository.disableRES()
        }
    }

    fun enable() {
        viewModelScope.launch {
            rationaleRepository.enableRES()
        }
    }

    companion object {
        fun provideFactory(
            rationaleRepository: RationaleRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(rationaleRepository) as T
            }
        }
    }
}