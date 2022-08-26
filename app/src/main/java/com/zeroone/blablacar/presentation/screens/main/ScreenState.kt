package com.zeroone.blablacar.presentation.screens.main

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.runtime.mutableStateListOf
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ScreenState(
    val navController: NavHostController,
    val scaffoldState: ScaffoldState,
    val coroutineScope: CoroutineScope,
) {

    fun navigateTo(route:String){
        navController.navigate(route=route)
    }

    fun navigateToBack(){
        navController.popBackStack()
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

    val getBottomBarNavItems : List<BottomNavItem> = mutableStateListOf(
        BottomNavItem.Home,
        BottomNavItem.NewPost,
        BottomNavItem.Profile,
    )
}

