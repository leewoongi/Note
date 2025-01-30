package com.woongi.home

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.woongi.detail.DetailActivity
import com.woongi.home.model.constants.NavigationEvent
import com.woongi.home.ui.Note
import com.woongi.home.ui.Toolbar

@Composable
fun MyAppScreen(

){
    val viewModel : MainViewModel = hiltViewModel()
    val context = LocalContext.current // Intent 실행을 위한 Context

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


    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
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
}