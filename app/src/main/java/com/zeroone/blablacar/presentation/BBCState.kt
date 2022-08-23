package com.zeroone.blablacar.presentation

import androidx.compose.material.ScaffoldState

import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BBCState(
    val navController: NavHostController,
    val scaffoldState: ScaffoldState,
    val coroutineScope: CoroutineScope,
) {

    fun navigateTo(route:String){
        navController.navigate(route=route)
    }

    private var snackbarJob: Job? = null
    fun showSnackbar(message: String, duration: SnackbarDuration = SnackbarDuration.Short) {
        snackbarJob?.cancel()
        snackbarJob = coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(
                message = message,
                duration = duration
            )
        }
    }
}

@Composable
fun rememberBBCState(
    navController: NavHostController = rememberNavController(),
    scaffoldState: ScaffoldState = rememberScaffoldState(
        snackbarHostState = remember {
            SnackbarHostState()
        }
    ),
    scope: CoroutineScope = rememberCoroutineScope()
) = remember(navController, scaffoldState, scope) {
    BBCState(

        navController = navController,
        scaffoldState = scaffoldState,
        coroutineScope = scope
    )
}