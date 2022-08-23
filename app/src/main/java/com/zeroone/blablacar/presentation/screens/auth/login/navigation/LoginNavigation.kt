package com.zeroone.blablacar.presentation.screens.auth.login.navigation

import android.util.Log
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zeroone.blablacar.presentation.screens.Screen
import com.zeroone.blablacar.presentation.screens.auth.login.LoginScreen
import com.zeroone.blablacar.utils.TAG

fun NavGraphBuilder.loginGraph(
    modifier: Modifier = Modifier,
    navigateToSignUp: () -> Unit,
    navigateToHome: () -> Unit,
    googleOnClick: () -> Unit,
    fbOnClick: () -> Unit,
) {
    Log.d(TAG, "loginGraph: ")
    composable(route = Screen.Login.route) {
        LoginScreen(
            modifier= modifier,
            navigateToSignUp=navigateToSignUp,
            navigateToHome =navigateToHome,
            googleOnClick = googleOnClick,
            fbOnClick = fbOnClick
        )
    }
}