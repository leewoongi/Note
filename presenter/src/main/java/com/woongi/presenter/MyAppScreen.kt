package com.woongi.presenter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.woongi.presenter.ui.Note
import com.woongi.presenter.ui.Toolbar

@Composable
fun MyAppScreen(

){
    val viewModel : MainViewModel = hiltViewModel()

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
            onClickDownload = { viewModel.save() }
        )

        Note(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red)
        )
    }
}