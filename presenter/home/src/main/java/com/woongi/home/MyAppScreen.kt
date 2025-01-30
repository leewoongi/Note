package com.woongi.home

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.woongi.detail.DetailActivity
import com.woongi.home.model.constants.NavigationEvent
import com.woongi.home.ui.Note
import com.woongi.home.ui.Toolbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun MyAppScreen(

) {
    val viewModel: MainViewModel = hiltViewModel()
    val context = LocalContext.current // Intent 실행을 위한 Context
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

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
    ){
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
                    onClickDownload = { viewModel.save() },
                    onClickLoad = { viewModel.navigateDetail() }
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
        }
    }
}
