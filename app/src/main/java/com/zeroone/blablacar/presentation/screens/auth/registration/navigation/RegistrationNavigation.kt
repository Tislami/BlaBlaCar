package com.zeroone.blablacar.presentation.screens.auth.registration.navigation

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.zeroone.blablacar.presentation.screens.main.Screen
import com.zeroone.blablacar.presentation.screens.auth.registration.RegistrationScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.registrationGraph(
    modifier: Modifier = Modifier,
    navigateToSignIn: ()->Unit,
    navigateToHome: ()->Unit,
    googleOnClick: () -> Unit,
    fbOnClick: () -> Unit,
) {
    Log.d("Graph", "registrationGraph: ")
    composable(route = Screen.Registration.route) {
        RegistrationScreen(
            modifier= modifier,
            navigateToSignIn =navigateToSignIn,
            navigateToHome =navigateToHome,
            googleOnClick = googleOnClick,
            fbOnClick = fbOnClick
        )
    }
}