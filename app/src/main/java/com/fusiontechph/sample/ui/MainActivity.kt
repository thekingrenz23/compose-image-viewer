package com.fusiontechph.sample.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fusiontechph.sample.SampleApp
import com.fusiontechph.sample.ui.home.Home
import com.fusiontechph.sample.ui.home.HomeViewModel
import com.fusiontechph.sample.ui.theme.ComposeimageviewerTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {

    val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = (application as SampleApp).container

        setContent {
            ComposeimageviewerTheme {
                Log.i("RATIONALE", "before view model dec")
                val homeViewModel: HomeViewModel = viewModel(
                    factory = HomeViewModel.provideFactory(appContainer.rationaleRepository)
                )

                Log.i("RATIONALE", "before collect state")
                val rational by homeViewModel.showReadExternalStorageRationale.collectAsState()

                Home(
                    showReadExternalStorageRationale = rational,
                    enable = {
                       homeViewModel.enable()
                    },
                    disable = {
                        homeViewModel.disable()
                    }
                )
            }
        }
    }
}