package com.woongi.core.extension

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun Modifier.singleClick(
    interval: Long = 1000L, // 클릭 간격 (밀리초)
    onSingleClick: () -> Unit,
): Modifier {
    var lastClickTime by remember { mutableLongStateOf(0L) }

    return this.then(
        Modifier.clickable(
            onClick = {
                val currentTime = System.currentTimeMillis()
                if (currentTime - lastClickTime >= interval) {
                    lastClickTime = currentTime
                    onSingleClick()
                }
            }
        )
    )
}