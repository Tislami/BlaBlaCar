package com.zeroone.blablacar.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.zeroone.blablacar.presentation.BBCState
import com.zeroone.blablacar.presentation.screens.auth.login.navigation.loginGraph
import com.zeroone.blablacar.presentation.screens.auth.registration.navigation.registrationGraph
import com.zeroone.blablacar.presentation.screens.home.navigation.homeGraph
import com.zeroone.blablacar.presentation.screens.post.navigation.postGraph
import com.zeroone.blablacar.presentation.screens.user.profile.navigation.profileGraph
import com.zeroone.blablacar.utils.TAG


@Composable
fun BBCNavigation() {

    val bbcState = BBCState(
        navController = rememberNavController(),
        scaffoldState = rememberScaffoldState(),
        coroutineScope = rememberCoroutineScope(),
    )

    Log.d(TAG, "BBCNavigation: ")
    Scaffold(
        scaffoldState = bbcState.scaffoldState,
        content = {
            Log.d(TAG, "BBCNavigation: Content")
            NavHostController(modifier = Modifier.padding(it), bbcState)
        },
    )

}


@Composable
private fun NavHostController(
    modifier: Modifier = Modifier,
    bbcState: BBCState
) {
    NavHost(
        navController = bbcState.navController,
        startDestination = Screen.Registration.route,
        modifier = modifier
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
            postClick = {
                Log.d(TAG, "NavHostController: cliked $it")
                bbcState.navigateTo(Screen.Post.route + "/$it")
            }
        )
        profileGraph()
        postGraph()
    }
}
