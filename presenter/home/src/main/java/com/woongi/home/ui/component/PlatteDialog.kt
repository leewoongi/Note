package com.woongi.home.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun PlatteDialog(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    selectColorProperty: (Color, Float, Float) -> Unit,
    onDismissRequest: () -> Unit = {}
) {
    val colors = List(20) { Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat(), 1f) }

    var selectedColor by remember { mutableStateOf(Color.Black) }
    var brightness by remember { mutableFloatStateOf(0.5f) }
    var opacity by remember { mutableFloatStateOf(1f) }


    AlertDialog(
        containerColor = Color.White,
        title = {
            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "색상 선택")

                IconButton(
                    onClick = { onDismissRequest() }
                ) {
                    Icon(icon, contentDescription = "CloseIcon")
                }
            }
        },
        text = {
            Column(modifier = modifier) {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp), // 양옆 여백 추가
                    horizontalArrangement = Arrangement.spacedBy(8.dp) // 아이템 간 간격
                ) {
                    items(10) { index ->
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(colors[index], RoundedCornerShape(20.dp))
                                .clickable { selectedColor = colors[index] }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp), // 양옆 여백 추가
                    horizontalArrangement = Arrangement.spacedBy(8.dp) // 아이템 간 간격
                ) {
                    items(10) { index ->
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(colors[index + 10], RoundedCornerShape(20.dp))
                                .clickable { selectedColor = colors[index + 10] }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

                Text(text = "brightness: ${brightness.toInt()}")
                Slider(
                    value = brightness,
                    onValueChange = { brightness = it },
                    valueRange = 0f..100f
                )

                Text(text = "Opacity: ${(opacity * 100).toInt()}%")
                Slider(
                    value = opacity,
                    onValueChange = { opacity = it },
                    valueRange = 0f..1f
                )
            }
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .background(Color.Blue.copy(0.4f)),
                onClick = {
                    selectColorProperty(
                        selectedColor,
                        brightness,
                        opacity
                    )
                }
            ) {
                Text(
                    text = "확인",
                    color = Color.White
                )
            }
        },
        dismissButton = {}
    )
}