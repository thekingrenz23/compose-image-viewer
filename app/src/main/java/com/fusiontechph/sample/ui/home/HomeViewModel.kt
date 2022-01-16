package com.fusiontechph.sample.ui.home

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fusiontechph.sample.data.Result
import com.fusiontechph.sample.data.rationale.RationaleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val rationaleRepository: RationaleRepository
) : ViewModel() {

    val mutex = Mutex()

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
                is Result.Success -> {
                    mutex.withLock {
                        showReadExternalStorageRationale.value = result.data
                    }
                }
                is Result.Error -> {
                    mutex.withLock {
                        showReadExternalStorageRationale.value = false
                    }
                }
            }
        }
    }

    // disable rationale
    // we don't care if it is saved or not
    fun disable() {
        viewModelScope.launch {
            rationaleRepository.disableRES()
            getReadExternalStorageRationalePersistent()
        }
    }

    // enable rationale
    // we don't care if it is saved or not
    fun enable() {
        viewModelScope.launch {
            rationaleRepository.enableRES()
            getReadExternalStorageRationalePersistent()
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