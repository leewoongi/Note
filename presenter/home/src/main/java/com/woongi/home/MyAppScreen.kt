package com.woongi.home

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.woongi.detail.DetailActivity
import com.woongi.home.model.constants.NavigationEvent
import com.woongi.home.ui.Note
import com.woongi.home.ui.Toolbar
import com.woongi.home.ui.component.LinePropertiesDialog
import com.woongi.home.ui.component.PlatteDialog
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun MyAppScreen(

) {
    val context = LocalContext.current
    val viewModel: MainViewModel = hiltViewModel()

    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    var isPropertyPopupVisible by remember { mutableStateOf(false) }
    var isColorPopupVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.navigate.collect { event ->
            when (event) {
                is NavigationEvent.DETAIL -> {
                    val intent = Intent(context, DetailActivity::class.java)
                    context.startActivity(intent)
                }

                NavigationEvent.HOME -> {}
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.snackBar.collectLatest { message ->
            coroutineScope.launch {
                snackBarHostState.showSnackbar(
                    message = message
                )
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            snackbarHost = { SnackbarHost(snackBarHostState) }
        ) { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding)
            ) {
                Toolbar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .height(56.dp),
                    onClickPlatte = { isColorPopupVisible = true },
                    onClickDownload = { viewModel.save() },
                    onClickLoad = { viewModel.navigateDetail() },
                    onClickDrawing = { isPropertyPopupVisible = true }
                )

                Note(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    viewModel = viewModel
                )
            }

            Box(
                modifier = Modifier
                    .padding(scaffoldPadding)
                    .fillMaxSize(),
            )

            if (isPropertyPopupVisible) {
                LinePropertiesDialog(
                    modifier = Modifier.fillMaxWidth(),
                    onDismissRequest = { thickness, opacity ->
                        viewModel.updateThickness(thickness)
                        viewModel.updateOpacity(opacity)
                        isPropertyPopupVisible = false
                    },
                    icon = Icons.Default.Close,
                    thickness = viewModel.thickness.value,
                    opacity = viewModel.opacity.value
                )
            }

            if(isColorPopupVisible) {
                PlatteDialog(
                    modifier = Modifier.fillMaxWidth(),
                    icon = Icons.Default.Close,
                    selectColorProperty = { color, brightness, opacity ->
                        isColorPopupVisible = false
                        viewModel.updateColor(color, brightness, opacity)
                    },
                    onDismissRequest = { isColorPopupVisible = false }
                )
            }
        }
    }
}
