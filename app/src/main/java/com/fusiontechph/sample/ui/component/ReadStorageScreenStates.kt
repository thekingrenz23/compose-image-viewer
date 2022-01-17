package com.fusiontechph.sample.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fusiontechph.sample.ui.theme.ComposeimageviewerTheme

@Composable
fun ReadStorageRationale(
    onAllow: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Please turn on storage access to view photos",
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onAllow) {
            Text(text = "Turn on", textAlign = TextAlign.Center)
        }
    }
}

@Preview(name = "Rationale", showBackground = true)
@Composable
fun PreviewStorageRationale() {
    ComposeimageviewerTheme {
        ReadStorageRationale(
            onAllow = {}
        )
    }
}

@Composable
fun ReadStorageDenied(
    onOpenSettings: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Storage access is denied", textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Kooha does not have the permission required to view photos.",
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onOpenSettings) {
            Text(text = "Open settings", textAlign = TextAlign.Center)
        }
    }
}

@Preview(name = "Denied", showBackground = true)
@Composable
fun PreviewStorageDenied() {
    ComposeimageviewerTheme {
        ReadStorageDenied(
            onOpenSettings = {}
        )
    }
}