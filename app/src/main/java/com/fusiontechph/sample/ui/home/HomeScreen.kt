package com.fusiontechph.sample.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Home(
    showReadExternalStorageRationale: Boolean,
    disable: () -> Unit,
    enable: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = showReadExternalStorageRationale.toString())
        Button(onClick = { disable() }) {
            Text(text = "disable")
        }
        Button(onClick = { enable() }) {
            Text(text = "enable")
        }
    }
}