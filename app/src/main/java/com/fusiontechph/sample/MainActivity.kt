package com.fusiontechph.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.fusiontechph.sample.data.AppContainer
import com.fusiontechph.sample.data.AppContainerImpl
import com.fusiontechph.sample.ui.theme.ComposeimageviewerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = (application as SampleApp).container

        setContent {
            ComposeimageviewerTheme {
                Text(text = "Hello Android")
            }
        }
    }
}