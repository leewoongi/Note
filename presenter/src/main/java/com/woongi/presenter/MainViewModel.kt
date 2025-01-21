package com.woongi.presenter

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(

): ViewModel() {

    init {
        getUsers()
    }

    private fun getUsers(
        searchKeyword: String = "woongi"
    ) {

    }
}