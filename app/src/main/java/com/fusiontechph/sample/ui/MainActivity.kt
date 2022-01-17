package com.fusiontechph.sample.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
                    openSettings = {
                        startActivity(
                            Intent(
                                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.fromParts("package", packageName, null)
                            )
                        )
                    }
                )
            }
        }
    }
}