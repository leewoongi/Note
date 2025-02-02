package com.woongi.home.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun LinePropertiesDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: (Float, Float) -> Unit,
    icon: ImageVector,
) {
    var lineThickness by remember { mutableFloatStateOf(2f) }
    var lineOpacity by remember { mutableFloatStateOf(1f) }

    AlertDialog(
        title = {
            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = { onDismissRequest(lineThickness, lineOpacity) }
                ) {
                    Icon(icon, contentDescription = "CloseIcon")
                }
            }
        },
        text = {
            Column {
                // 선의 굵기 조절 슬라이더
                Text(
                    text = "Line Thickness: ${lineThickness.toInt()}",
                    fontWeight = FontWeight.Bold
                )
                Slider(
                    value = lineThickness,
                    onValueChange = { newValue ->
                        lineThickness = newValue
                    },
                    valueRange = 1f..10f,  // 선 굵기 최소 1, 최대 10
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 선의 투명도 조절 슬라이더
                Text(
                    text = "Line Opacity: ${(lineOpacity * 100).toInt()}%",
                    fontWeight = FontWeight.Bold
                )
                Slider(
                    value = lineOpacity,
                    onValueChange = { newValue ->
                        lineOpacity = newValue
                    },
                    valueRange = 0f..1f,  // 투명도 최소 0, 최대 1
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        onDismissRequest = {
            onDismissRequest(lineThickness, lineOpacity)
        },
        confirmButton = {},
        dismissButton = {}
    )
}