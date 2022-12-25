package com.zeroone.blablacar.presentation.screens.user.profile.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.composable
import com.zeroone.blablacar.presentation.screens.main.Graph
import com.zeroone.blablacar.presentation.screens.main.Routes
import com.zeroone.blablacar.presentation.screens.user.profile.ProfileRoute
import javax.annotation.concurrent.Immutable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.profileGraph(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    navigation(
        route = Graph.PROFILE,
        startDestination = ProfileRoutes.About.route,
    ) {
        composable(route = Routes.Profile.route) {
            ProfileRoute(
                modifier = modifier
            )
        }
    }

}

sealed class ProfileRoutes(val route: String) {
    @Immutable
    object Account : ProfileRoutes("Account")

    @Immutable
    object About : ProfileRoutes("About")

}
