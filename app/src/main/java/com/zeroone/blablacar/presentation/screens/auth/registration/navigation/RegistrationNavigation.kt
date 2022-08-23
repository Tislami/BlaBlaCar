package com.zeroone.blablacar.presentation.screens.auth.registration.navigation

import android.util.Log
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zeroone.blablacar.presentation.screens.Screen
import com.zeroone.blablacar.presentation.screens.auth.registration.RegistrationScreen
import com.zeroone.blablacar.utils.TAG

fun NavGraphBuilder.registrationGraph(
    modifier: Modifier = Modifier,
    navigateToSignIn: ()->Unit,
    navigateToHome: ()->Unit,
    googleOnClick: () -> Unit,
    fbOnClick: () -> Unit,
) {
    Log.d(TAG, "registrationGraph: ")
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