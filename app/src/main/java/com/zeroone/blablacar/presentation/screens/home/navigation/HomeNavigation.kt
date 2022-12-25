package com.zeroone.blablacar.presentation.screens.home.navigation

import androidx.compose.animation.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.zeroone.blablacar.presentation.screens.main.Routes
import com.zeroone.blablacar.presentation.screens.home.HomeRoute

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.homeGraph(
    modifier: Modifier = Modifier,
    backOnClick:()->Unit,
    postOnClick:(Int) -> Unit
) {
    composable(route = Routes.Home.route) {
        HomeRoute(
            modifier= modifier
        )
    }
}