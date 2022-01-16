package com.fusiontechph.sample.data

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.fusiontechph.sample.data.rationale.RationaleRepository
import com.fusiontechph.sample.data.rationale.impl.DSRationaleRepository

private const val rationaleStorageName = "rationale"

interface AppContainer {
    val rationaleRepository: RationaleRepository
}

class AppContainerImpl(private val context: Context) : AppContainer {

    private val Context.dataStore by preferencesDataStore(name = rationaleStorageName)

    override val rationaleRepository by lazy {
        DSRationaleRepository(context.dataStore)
    }

}