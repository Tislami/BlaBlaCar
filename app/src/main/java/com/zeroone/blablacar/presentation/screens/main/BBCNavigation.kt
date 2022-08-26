package com.zeroone.blablacar.presentation.screens.main

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.zeroone.blablacar.presentation.screens.auth.login.navigation.loginGraph
import com.zeroone.blablacar.presentation.screens.auth.registration.navigation.registrationGraph
import com.zeroone.blablacar.presentation.screens.home.navigation.homeGraph
import com.zeroone.blablacar.presentation.screens.posts.newpostscreen.navigation.newPostGraph
import com.zeroone.blablacar.presentation.screens.posts.sharedpost.navigation.expandedPostGraph
import com.zeroone.blablacar.presentation.screens.user.profile.navigation.profileGraph

@Composable
fun BBCNavigation(screenState: ScreenState) {

    Log.d("Screen", "BBCNavigation: ")
    val navBackStackEntry by screenState.navController.currentBackStackEntryAsState()

    Scaffold(
        scaffoldState = screenState.scaffoldState,
        content = {
            Log.d("Screen", "BBCNavigation: Content")
            NavHostController(screenState = screenState)
        },
        bottomBar = {
            Log.d("Screen", "BBCNavigation: BottomBar")
            when (navBackStackEntry?.destination?.route) {
                Screen.Login.route -> return@Scaffold
                Screen.Registration.route -> return@Scaffold
                Screen.NewPost.route -> return@Scaffold
            }
            BBCBottomBar(screenState = screenState,navBackStackEntry)
        }
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun NavHostController(
    screenState: ScreenState) {
    AnimatedNavHost(
        navController = screenState.navController,
        startDestination = Screen.Home.route,
        enterTransition = { fadeIn(animationSpec = tween(0)) },
        exitTransition = { fadeOut(animationSpec = tween(0)) },
        popEnterTransition = { fadeIn(animationSpec = tween(0)) },
        popExitTransition = { fadeOut(animationSpec = tween(0)) },
    ) {
        registrationGraph(
            navigateToHome = { screenState.navigateTo(Screen.Home.route) },
            navigateToSignIn = { screenState.navigateTo(Screen.Login.route) },
            googleOnClick = { screenState.showSnackbar("Google") },
            fbOnClick = { screenState.showSnackbar("FB") },
        )
        loginGraph(
            navigateToHome = { screenState.navigateTo(Screen.Home.route) },
            navigateToSignUp = { screenState.navigateTo(Screen.Registration.route) },
            googleOnClick = { screenState.showSnackbar("Google") },
            fbOnClick = { screenState.showSnackbar("FB") },
        )
        homeGraph(
            backOnClick = { screenState.navigateTo(Screen.Registration.route) },
            postOnClick = { screenState.navigateTo(Screen.ExpandedPost.route + "/$it") }
        )
        profileGraph(
            backOnClick = { screenState.navigateToBack() },
            settingOnClick = { screenState.showSnackbar("Not ready yet") },
            )

        newPostGraph(
            backOnClick = { screenState.navigateToBack() },
            shareClick = { screenState.showSnackbar("Not ready yet")}
        )
        expandedPostGraph()
    }
}

