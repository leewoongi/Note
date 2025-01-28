package com.woongi.presenter.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.woongi.core.R

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    onClickDownload: () -> Unit = {}
){
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterEnd)
                .clickable {
                    onClickDownload()
                },
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_download),
            contentDescription = "download",
            tint = Color.Black,
        )
    }
}