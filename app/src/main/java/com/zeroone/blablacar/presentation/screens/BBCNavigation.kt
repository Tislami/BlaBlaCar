package com.zeroone.blablacar.presentation.screens

import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.zeroone.blablacar.domain.model.defaultPost
import com.zeroone.blablacar.presentation.screens.auth.login.LoginScreen
import com.zeroone.blablacar.presentation.screens.auth.login.LoginTopAppBar
import com.zeroone.blablacar.presentation.screens.auth.registration.RegistrationScreen
import com.zeroone.blablacar.presentation.screens.auth.registration.RegistrationTopAppBar
import com.zeroone.blablacar.presentation.screens.home.HomeScreen
import com.zeroone.blablacar.presentation.screens.home.HomeTopAppBar
import com.zeroone.blablacar.presentation.screens.post.PostScreen
import com.zeroone.blablacar.presentation.screens.post.PostTopAppBar

@Composable
fun BBCNavigation() {

    val contentNavController = rememberNavController()

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            Log.d("Bars","TopBar ") },
        content = { NavHostContentController(contentNavController)
            Log.d("Bars","Content")

        },
        bottomBar = {
            Log.d("Bars","BottomBar")

        }
    )

}

@Composable
private fun NavHostContentController(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.Registration.route) {
        composable(route = Screens.Registration.route) { RegistrationScreen(navController) }
        composable(route = Screens.Login.route) { LoginScreen(navController) }
        composable(route = Screens.Home.route) { HomeScreen(navController) }

        composable(
            route = Screens.Post.route + "/{id}",
            arguments = listOf(navArgument(name = "id") {
                type = NavType.IntType
                defaultValue = 0
            })
        ) {

            PostScreen(navController = navController, defaultPost) }
    }
}