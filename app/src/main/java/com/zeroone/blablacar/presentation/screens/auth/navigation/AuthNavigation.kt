package com.zeroone.blablacar.presentation.screens.auth.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import com.zeroone.blablacar.presentation.screens.auth.AuthScreen
import com.zeroone.blablacar.presentation.screens.auth.AuthSelectionScreen
import javax.annotation.concurrent.Immutable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.authNavigation(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    composable(route = AuthScreens.AuthSelection.route+"/{introSelection}",
        arguments = listOf(
            navArgument("introSelection") {
                type = NavType.StringType
            },
        )
    ) { backStackEntry ->
        AuthSelectionScreen(
            modifier= modifier,
            selection=backStackEntry.arguments?.getString("introSelection")!!,
            navController = navController
        )
    }

    composable(AuthScreens.AuthScreen.route+"/{selection}",
        arguments = listOf(
            navArgument("selection") {
                type = NavType.StringType
            },
        )
    ) { backStackEntry ->
        AuthScreen(
            modifier = modifier,
            navController = navController,
            selection=backStackEntry.arguments?.getString("selection")!!,
        )
    }
}

sealed class AuthScreens(val route: String) {
    @Immutable
    object AuthSelection : AuthScreens("auth_selection")
    object AuthScreen : AuthScreens("auth_screen")
}
