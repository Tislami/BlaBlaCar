package com.zeroone.blablacar.presentation.screens.home.navigation

import android.util.Log
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zeroone.blablacar.presentation.screens.Screen
import com.zeroone.blablacar.presentation.screens.auth.login.LoginScreen
import com.zeroone.blablacar.presentation.screens.home.HomeScreen
import com.zeroone.blablacar.utils.TAG

fun NavGraphBuilder.homeGraph(
    modifier: Modifier = Modifier,
    backOnClick:()->Unit,
    postClick:(Int) -> Unit
) {
    Log.d(TAG, "homeGraph: ")
    composable(route = Screen.Home.route) {
        HomeScreen(
            modifier= modifier,
            backOnClick = backOnClick,
            postClick = postClick
        )
    }
}