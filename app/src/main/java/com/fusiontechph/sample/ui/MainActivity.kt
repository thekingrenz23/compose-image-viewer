package com.fusiontechph.sample.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fusiontechph.sample.SampleApp
import com.fusiontechph.sample.ui.home.Home
import com.fusiontechph.sample.ui.home.HomeViewModel
import com.fusiontechph.sample.ui.theme.ComposeimageviewerTheme

class MainActivity : ComponentActivity() {

    val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = (application as SampleApp).container

        setContent {
            ComposeimageviewerTheme {
                val homeViewModel: HomeViewModel = viewModel(
                    factory = HomeViewModel.provideFactory(appContainer.rationaleRepository)
                )

                Home(
                    viewModel = homeViewModel,
                    disable = {
                        homeViewModel.disable()
                    }
                )
            }
        }
    }
}