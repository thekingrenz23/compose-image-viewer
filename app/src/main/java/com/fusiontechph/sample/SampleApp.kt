package com.fusiontechph.sample

import android.app.Application
import com.fusiontechph.sample.data.AppContainer
import com.fusiontechph.sample.data.AppContainerImpl

class SampleApp : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }

}