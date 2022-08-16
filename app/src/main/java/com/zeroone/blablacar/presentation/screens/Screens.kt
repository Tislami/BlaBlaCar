package com.zeroone.blablacar.presentation.screens

import androidx.compose.runtime.Composable
import com.zeroone.blablacar.presentation.screens.auth.AuthScreen
import com.zeroone.blablacar.presentation.screens.auth.login.LoginScreen

sealed class Screens(val route: String) {
    object Registration : Screens("registration")
    object Login : Screens("login")
    object Home : Screens("home")
    object Post : Screens("post")
}
