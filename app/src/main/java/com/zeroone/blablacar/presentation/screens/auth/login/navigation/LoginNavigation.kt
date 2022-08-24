package com.zeroone.blablacar.presentation.screens.auth.login.navigation

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.zeroone.blablacar.presentation.screens.main.Screen
import com.zeroone.blablacar.presentation.screens.auth.login.LoginScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.loginGraph(
    modifier: Modifier = Modifier,
    navigateToSignUp: () -> Unit,
    navigateToHome: () -> Unit,
    googleOnClick: () -> Unit,
    fbOnClick: () -> Unit,
) {
    Log.d("Graph", "loginGraph: ")

    composable(Screen.Login.route,){
        LoginScreen(
            modifier= modifier,
            navigateToSignUp=navigateToSignUp,
            navigateToHome =navigateToHome,
            googleOnClick = googleOnClick,
            fbOnClick = fbOnClick,
        )
    }

}