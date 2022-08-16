package com.zeroone.blablacar.presentation.screens

import android.util.Log
import androidx.compose.material.*
import androidx.compose.runtime.Composable


object BBCScaffold {
    var topBar: @Composable () -> Unit = {}
    var content: @Composable () -> Unit = {}
    val bottomBar: @Composable () -> Unit = {}


    @Composable
    fun BBCScaffold() {

        val scaffoldState = rememberScaffoldState()

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { topBar() },
            content = { content() },
            bottomBar = { bottomBar() }
        )
    }
}