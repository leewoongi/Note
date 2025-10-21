package com.woongi.home.extension

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

@Composable
fun rememberPermissionLauncher(
    onGranted: () -> Unit,
    onDenied: () -> Unit = {  }
): ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>> {
    return rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        val allGranted = result.values.all { it }
        if (allGranted) onGranted() else onDenied()
    }
}

@Composable
fun rememberGalleryLauncher(
    onImageSelected: (Uri) -> Unit,
    onCancelled: () -> Unit = {}
): ManagedActivityResultLauncher<String, Uri?> {
    return rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->

        if (uri != null) {
            onImageSelected(uri)
        } else {
            onCancelled()
        }
    }
}
