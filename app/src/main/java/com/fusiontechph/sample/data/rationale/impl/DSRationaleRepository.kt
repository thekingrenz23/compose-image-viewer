package com.fusiontechph.sample.data.rationale.impl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.fusiontechph.sample.data.rationale.RationaleRepository

class DSRationaleRepository(val dataStore: DataStore<Preferences>) : RationaleRepository {


}