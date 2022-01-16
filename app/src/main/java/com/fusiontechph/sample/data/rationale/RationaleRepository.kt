package com.fusiontechph.sample.data.rationale

import com.fusiontechph.sample.data.Result
import com.fusiontechph.sample.model.Rationale
import kotlinx.coroutines.flow.Flow

interface RationaleRepository {

    // track if the user wants to see the rationale
    val showReadExternalStorageRationale: Flow<Boolean>

    suspend fun getShowReadExternalStorageRationale(): Result<Boolean>
    suspend fun disableRES()
    suspend fun enableRES()
}