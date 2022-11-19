package com.zeroone.blablacar.presentation.screens.home.navigation

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.zeroone.blablacar.presentation.screens.main.Screen
import com.zeroone.blablacar.presentation.screens.home.HomeScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.homeGraph(
    modifier: Modifier = Modifier,
    backOnClick:()->Unit,
    postOnClick:(Int) -> Unit
) {
    composable(route = Screen.Home.route) {
        HomeScreen(
            modifier= modifier,
            backOnClick = backOnClick,
            postOnClick = postOnClick
        )
    }
}