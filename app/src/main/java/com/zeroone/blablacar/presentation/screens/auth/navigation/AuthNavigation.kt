package com.zeroone.blablacar.presentation.screens.auth.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.zeroone.blablacar.presentation.screens.auth.login.LoginRoute
import com.zeroone.blablacar.presentation.screens.auth.registration.RegistrationRoute
import javax.annotation.concurrent.Immutable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.authNavigation(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    composable(route = AuthScreens.Registration.route)
    {
       RegistrationRoute(
           modifier = modifier,
           backOnClick = {navController.popBackStack()}
       )
    }

    composable(AuthScreens.Login.route) {
        LoginRoute(
            modifier = modifier,
            backOnClick = {navController.popBackStack()}
        )
    }
}

sealed class AuthScreens(val route: String) {
    @Immutable
    object Registration: AuthScreens("Registration")
    object Login : AuthScreens("Login")
}
