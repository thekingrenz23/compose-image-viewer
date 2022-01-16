package com.fusiontechph.sample.data.rationale.impl

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.fusiontechph.sample.data.rationale.RationaleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import java.io.IOException

class DSRationaleRepository(val dataStore: DataStore<Preferences>) : RationaleRepository {

    object Keys {
        val showReadExternalStorageRationale =
            booleanPreferencesKey("showReadExternalStorageRationale")
    }

    // if showReadExternalStorageRationale is null then default to true
    private inline val Preferences.showReadExternalStorageRationale
        get() = this[Keys.showReadExternalStorageRationale] ?: true

    // check if user still wants to see the rationale
    override val showReadExternalStorageRationale: Flow<Boolean> =
        dataStore.data
            .catch {
                // throws an IOException when an error is encountered when reading data
                if (it is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }
            .map { preferences ->
                Log.i("RATIONALE", "map operator executed")
                preferences.showReadExternalStorageRationale
            }
            .distinctUntilChanged()

    // disable read external storage rationale
    override suspend fun disableRES() {
        dataStore.edit {
            it[Keys.showReadExternalStorageRationale] = false
        }
    }

    // enable read external storage rationale
    override suspend fun enableRES() {
        dataStore.edit {
            it[Keys.showReadExternalStorageRationale] = true
        }
    }
}