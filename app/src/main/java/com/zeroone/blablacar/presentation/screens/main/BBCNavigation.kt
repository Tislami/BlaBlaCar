package com.zeroone.blablacar.presentation.screens.main

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.zeroone.blablacar.presentation.screens.auth.login.navigation.loginGraph
import com.zeroone.blablacar.presentation.screens.auth.registration.navigation.registrationGraph
import com.zeroone.blablacar.presentation.screens.home.navigation.homeGraph
import com.zeroone.blablacar.presentation.screens.post.navigation.postGraph
import com.zeroone.blablacar.presentation.screens.user.profile.navigation.profileGraph

@Composable
fun BBCNavigation(bbcState: BBCState) {

    Log.d("Screen", "BBCNavigation: ")

    val navBackStackEntry by bbcState.navController.currentBackStackEntryAsState()

    Scaffold(
        content = {
            Log.d("Screen", "BBCNavigation: Content")
            NavHostController(bbcState)
        },
        bottomBar = {
            Log.d("Screen", "BBCNavigation: BottomBar")
            when (navBackStackEntry?.destination?.route) {
                Screen.Home.route -> BBCBottomBar(bbcState = bbcState)
                Screen.Profile.route -> BBCBottomBar(bbcState = bbcState)
            }
        }
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun NavHostController(bbcState: BBCState) {
    AnimatedNavHost(
        navController = bbcState.navController,
        startDestination = Screen.Home.route,
        enterTransition = { fadeIn(animationSpec = tween(0)) },
        exitTransition = { fadeOut(animationSpec = tween(0)) },
        popEnterTransition = { fadeIn(animationSpec = tween(0)) },
        popExitTransition = { fadeOut(animationSpec = tween(0)) },
    ) {
        registrationGraph(
            navigateToHome = { bbcState.navigateTo(Screen.Home.route) },
            navigateToSignIn = { bbcState.navigateTo(Screen.Login.route) },
            googleOnClick = { bbcState.showSnackbar("Google") },
            fbOnClick = { bbcState.showSnackbar("FB") },
        )
        loginGraph(
            navigateToHome = { bbcState.navigateTo(Screen.Home.route) },
            navigateToSignUp = { bbcState.navigateTo(Screen.Registration.route) },
            googleOnClick = { bbcState.showSnackbar("Google") },
            fbOnClick = { bbcState.showSnackbar("FB") },
        )
        homeGraph(
            backOnClick = { bbcState.navigateTo(Screen.Registration.route) },
            postClick = { bbcState.navigateTo(Screen.Post.route + "/$it") }
        )
        profileGraph()
        postGraph()
    }
}

