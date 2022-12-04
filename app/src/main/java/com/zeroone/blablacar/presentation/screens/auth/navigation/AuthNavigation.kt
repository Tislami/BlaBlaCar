package com.zeroone.blablacar.presentation.screens.auth.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.zeroone.blablacar.presentation.screens.auth.IntroScreen
import com.zeroone.blablacar.presentation.screens.auth.login.LoginRoute
import com.zeroone.blablacar.presentation.screens.auth.registration.RegistrationRoute
import com.zeroone.blablacar.presentation.screens.main.Graph
import com.zeroone.blablacar.presentation.screens.main.Screen
import javax.annotation.concurrent.Immutable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.authNavigation(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreens.Intro.route,
    ) {
        composable(route = Screen.Intro.route) {
            IntroScreen(
                modifier = modifier,
                signUpOnClick = { navController.navigate(AuthScreens.Registration.route) },
                loginOnClick = { navController.navigate(AuthScreens.Login.route) }
            )
        }

        composable(route = AuthScreens.Registration.route)
        {
            RegistrationRoute(
                modifier = modifier,
                backOnClick = { navController.popBackStack() }
            )
        }

        composable(AuthScreens.Login.route) {
            LoginRoute(
                modifier = modifier,
                backOnClick = { navController.popBackStack() }
            )
        }
    }
}

sealed class AuthScreens(val route: String) {
    @Immutable
    object Registration : AuthScreens("Registration")
    @Immutable
    object Login : AuthScreens("Login")
    @Immutable
    object Intro : AuthScreens("intro")
}
