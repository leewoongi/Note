package com.woongi.presenter

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MyAppScreen(){
    val viewModel : MainViewModel = hiltViewModel()
    Text(" My App Screen")
}