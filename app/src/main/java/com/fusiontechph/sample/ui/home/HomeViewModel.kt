package com.fusiontechph.sample.ui.home

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fusiontechph.sample.data.Result
import com.fusiontechph.sample.data.rationale.RationaleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val rationaleRepository: RationaleRepository
) : ViewModel() {

    // state of the rationale
    private val showReadExternalStorageRationale = MutableStateFlow(false)
    fun observeRationale(): Flow<Boolean> = showReadExternalStorageRationale

    init {
        // load the rational
        getReadExternalStorageRationalePersistent()
    }

    // fetch the rationale on the data store
    fun getReadExternalStorageRationalePersistent() {
        viewModelScope.launch {
            val result = rationaleRepository.getShowReadExternalStorageRationale()
            when (result) {
                is Result.Success -> showReadExternalStorageRationale.value = result.data
                is Result.Error -> showReadExternalStorageRationale.value = false
            }
        }
    }

    // disable rationale
    fun disable() {
        viewModelScope.launch {
            rationaleRepository.disableRES()
        }
    }

    // enable rationale
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