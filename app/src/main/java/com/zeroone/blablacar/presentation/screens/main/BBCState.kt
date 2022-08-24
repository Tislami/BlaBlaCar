package com.zeroone.blablacar.presentation.screens.main

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
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

    val getBottomBarNavItems : List<BottomNavItem> = mutableStateListOf(
        BottomNavItem.Home,
        BottomNavItem.Post,
        BottomNavItem.Profile,
    )

    @Composable
    fun GetCurrentDestination() : NavDestination? {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        return navBackStackEntry?.destination
    }

    @Composable
    fun ShouldShowBottomBar(): Boolean {
        return when(GetCurrentDestination()?.route){
            Screen.Registration.route -> false
            Screen.Login.route -> false
            else -> true
        }
    }
}

