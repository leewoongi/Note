package com.woongi.home.ui.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SaveDialog(
    onDismiss: () -> Unit,
    onPositiveClick: () -> Unit,
    onNegativeClick: (() -> Unit)? = null
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "저장") },
        text = { Text("덮어쓰기를 진행하시겠습니까?") },
        confirmButton = {
            Button(onClick = {
                onPositiveClick()
                onDismiss()
            }) {
                Text("새로 저장")
            }
        },
        dismissButton = {
            Button(onClick = {
                onNegativeClick?.invoke()
                onDismiss()
            }) {
                Text("덮어쓰기")
            }
        }
    )
}