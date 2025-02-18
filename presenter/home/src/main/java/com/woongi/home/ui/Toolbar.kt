package com.woongi.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.woongi.core.R
import com.woongi.core.extension.singleClick

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    onClickPlatte: () -> Unit = {},
    onClickDownload: () -> Unit = {},
    onClickLoad: () -> Unit = {},
    onClickDrawing: () -> Unit = {}
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .align(Alignment.CenterEnd),
            horizontalArrangement = Arrangement.End
        ) {

            Icon(
                modifier = Modifier
                    .singleClick(
                        onSingleClick = { onClickPlatte() }
                    ),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_platte),
                contentDescription = "download",
                tint = Color.Black,
            )

            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                modifier = Modifier
                    .singleClick(
                        onSingleClick = { onClickDrawing() }
                    ),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_drawing),
                contentDescription = "download",
                tint = Color.Black,
            )

            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                modifier = Modifier
                    .singleClick(
                        onSingleClick = { onClickLoad() }
                    ),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_load),
                contentDescription = "download",
                tint = Color.Black,
            )

            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                modifier = Modifier
                    .singleClick(
                        onSingleClick = { onClickDownload() }
                    ),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_download),
                contentDescription = "download",
                tint = Color.Black,
            )
        }
    }
}