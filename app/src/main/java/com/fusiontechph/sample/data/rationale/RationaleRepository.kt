package com.fusiontechph.sample.data.rationale

import com.fusiontechph.sample.data.Result
import kotlinx.coroutines.flow.Flow

interface RationaleRepository {

    // track if the user wants to see the rationale
    val showReadExternalStorageRationale: Flow<Boolean>

    // get if the user wants to see the rationale
    suspend fun getShowReadExternalStorageRationale(): Result<Boolean>

    // disable the rationale
    suspend fun disableRES()

    // enable the rationale
    suspend fun enableRES()
}