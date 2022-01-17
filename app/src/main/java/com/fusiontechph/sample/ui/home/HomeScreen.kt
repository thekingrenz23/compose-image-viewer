package com.fusiontechph.sample.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fusiontechph.renz.imageviewer.FusionImageViewer
import com.fusiontechph.sample.ui.component.ReadStorageDenied
import com.fusiontechph.sample.ui.component.ReadStorageRationale
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Home(
    viewModel: HomeViewModel,
    openSettings: () -> Unit
) {

    // Track if the user doesn't want to see the rationale any more.
    val rational by viewModel.observeRationale().collectAsState(true)

    // The state of the permission that is required
    val readStorageState =
        rememberPermissionState(android.Manifest.permission.READ_EXTERNAL_STORAGE)

    PermissionRequired(
        permissionState = readStorageState,
        permissionNotGrantedContent = {
            if (!rational) {
                ReadStorageDenied(
                    onOpenSettings = openSettings
                )
            } else {
                ReadStorageRationale(
                    onAllow = {
                        readStorageState.launchPermissionRequest()
                    }
                )
            }
        },
        permissionNotAvailableContent = {
            viewModel.disable()
            ReadStorageDenied(
                onOpenSettings = openSettings
            )
        }
    ) {
        FusionImageViewer()
    }
}