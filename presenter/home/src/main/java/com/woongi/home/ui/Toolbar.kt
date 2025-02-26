package com.woongi.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.woongi.core.R
import com.woongi.core.extension.singleClick
import com.woongi.home.MainViewModel
import com.woongi.home.model.constants.DrawingType

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    onClickPlatte: () -> Unit = {},
    onClickDrawing: () -> Unit = {},
) {
    val undo by viewModel.undo.collectAsState()
    val redo by viewModel.redo.collectAsState()

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
                        onSingleClick = { viewModel.updateMode(DrawingType.ERASE) }
                    ),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_erase),
                contentDescription = "erase",
                tint = Color.Black,
            )

            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                modifier = if(undo.isEmpty()) {
                    Modifier
                } else {
                    Modifier.singleClick( onSingleClick = { viewModel.undo() } )
                },
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_undo),
                contentDescription = "undo",
                tint = if(undo.isEmpty()) Color.Gray else Color.Black,
            )

            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                modifier = if(redo.isEmpty()) {
                    Modifier
                } else {
                    Modifier.singleClick( onSingleClick = { viewModel.redo() } )
                },
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_redo),
                contentDescription = "redo",
                tint = if(redo.isEmpty()) Color.Gray else Color.Black,
            )

            Spacer(modifier = Modifier.width(16.dp))

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
                        onSingleClick = {
                            viewModel.updateMode(DrawingType.DRAWING)
                            onClickDrawing()
                        }
                    ),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_drawing),
                contentDescription = "download",
                tint = Color.Black,
            )

            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                modifier = Modifier
                    .singleClick(
                        onSingleClick = { viewModel.navigateDetail() }
                    ),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_load),
                contentDescription = "download",
                tint = Color.Black,
            )

            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                modifier = Modifier
                    .singleClick(
                        onSingleClick = { viewModel.save() }
                    ),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_download),
                contentDescription = "download",
                tint = Color.Black,
            )
        }
    }
}