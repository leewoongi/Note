package com.woongi.home.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.woongi.home.model.uiModel.SaveDialogUiModel

@Composable
fun SaveDialog(
    uiModel: SaveDialogUiModel,
    onDismiss: () -> Unit,
    onPositiveClick: (String) -> Unit, // 제목을 받을 수 있도록 수정
    onNegativeClick: (String) -> Unit,
    showSnackBar: () -> Unit
) {
    var newTitle by remember { mutableStateOf(uiModel.pathTitle) } // 제목 입력을 위한 상태

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "저장") },
        text = {
            Column {
                Text("제목을 입력하세요:")
                TextField(
                    value = newTitle,
                    onValueChange = { newTitle = it },
                    placeholder = { Text("제목 입력") }
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                if (newTitle.isEmpty()) {
                    showSnackBar()
                } else {
                    onPositiveClick(newTitle)
                    onDismiss()
                }
            }) {
                Text("새로 저장")
            }
        },
        dismissButton = {
            if(uiModel.isVisibleNegativeButton){
                Button(onClick = {
                    if (newTitle.isEmpty()) {
                        showSnackBar()
                    } else {
                        onNegativeClick(newTitle)
                        onDismiss()
                    }
                }) {
                    Text("덮어쓰기")
                }
            }
        }
    )
}