package com.zeroone.blablacar.presentation.screens.main

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.zeroone.blablacar.presentation.screens.auth.navigation.authNavigation
import com.zeroone.blablacar.presentation.screens.home.HomeRoute
import com.zeroone.blablacar.presentation.screens.new_post.NewPostRoute
import com.zeroone.blablacar.presentation.screens.user.profile.ProfileRoute
import com.zeroone.blablacar.presentation.ui.theme.LocalDimensions

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BBCNavigation() {

    val navController = rememberAnimatedNavController()

    Scaffold(
        content = { innerPadding ->
            NavHostController(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = LocalDimensions.current.screenHorizontalPadding),
                navController = navController
            )
        },
        bottomBar = {
            BBCBottomBar(navController = navController)
        }
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun NavHostController(
    modifier: Modifier,
    navController: NavHostController,
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Routes.Home.route,
        enterTransition = { fadeIn(animationSpec = tween(0)) },
        exitTransition = { fadeOut(animationSpec = tween(0)) },
        popEnterTransition = { fadeIn(animationSpec = tween(0)) },
        popExitTransition = { fadeOut(animationSpec = tween(0)) },
    ) {

        authNavigation(modifier,navController)

        composable(Routes.Home.route) {
            HomeRoute(
                modifier = modifier,
            )
        }

        composable(Routes.NewPost.route) {
            NewPostRoute(
                modifier = modifier,
                navController= navController,
            )
        }

        composable(Routes.Profile.route) {
            ProfileRoute(modifier = modifier)
        }
    }
}

object Graph {
    const val AUTHENTICATION = "AUTH"
    const val PROFILE = "PROFILE"
    const val NEW_POST = "NEW_POST"
}

