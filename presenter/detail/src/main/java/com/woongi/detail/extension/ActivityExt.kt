package com.woongi.detail.extension

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

fun ComponentActivity.registerPermissionLauncher(
    onGranted: () -> Unit,
    onDenied: () -> Unit = { showPermissionSettingDialog() }
): ActivityResultLauncher<Array<String>> {
    return registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        val allGranted = result.values.all { it }
        if (allGranted) onGranted() else onDenied()
    }
}

fun ComponentActivity.requestGallery(
    onImageSelected: (Uri) -> Unit,
    onCancelled: () -> Unit = {}
): ActivityResultLauncher<String> {
    return registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            onImageSelected(uri)
        } else {
            onCancelled()
        }
    }
}

fun ComponentActivity.showPermissionSettingDialog() {
    AlertDialog.Builder(this)
        .setTitle("권한 필요")
        .setMessage("갤러리 접근 권한이 필요합니다. 설정에서 권한을 허용해주세요.")
        .setPositiveButton("설정으로 이동") { _, _ ->
            // 앱 설정 화면으로 이동
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", packageName, null)
            }
            startActivity(intent)
        }
        .setNegativeButton("취소") { dialog, _ ->
            dialog.dismiss()
        }
        .show()
}
