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
import com.zeroone.blablacar.presentation.screens.main.Routes
import javax.annotation.concurrent.Immutable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.authNavigation(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthRoutes.Intro.route,
    ) {
        composable(route = Routes.Intro.route) {
            IntroScreen(
                modifier = modifier,
                signUpOnClick = { navController.navigate(AuthRoutes.Registration.route) },
                loginOnClick = { navController.navigate(AuthRoutes.Login.route) }
            )
        }

        composable(route = AuthRoutes.Registration.route)
        {
            RegistrationRoute(
                modifier = modifier,
                backOnClick = { navController.popBackStack() }
            )
        }

        composable(AuthRoutes.Login.route) {
            LoginRoute(
                modifier = modifier,
                backOnClick = { navController.popBackStack() }
            )
        }
    }
}

sealed class AuthRoutes(val route: String) {
    @Immutable
    object Registration : AuthRoutes("Registration")
    @Immutable
    object Login : AuthRoutes("Login")
    @Immutable
    object Intro : AuthRoutes("intro")
}
