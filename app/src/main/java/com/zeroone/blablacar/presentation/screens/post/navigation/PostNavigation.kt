package com.zeroone.blablacar.presentation.screens.post.navigation

import android.util.Log
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.zeroone.blablacar.domain.model.defaultPost
import com.zeroone.blablacar.presentation.screens.Screen
import com.zeroone.blablacar.presentation.screens.auth.login.LoginScreen
import com.zeroone.blablacar.presentation.screens.home.HomeScreen
import com.zeroone.blablacar.presentation.screens.post.PostScreen
import com.zeroone.blablacar.presentation.screens.user.profile.ProfileScreen
import com.zeroone.blablacar.utils.TAG

fun NavGraphBuilder.postGraph(
) {
    Log.d(TAG, "postGraph: ")
    composable(
        route = Screen.Post.route + "/{id}",
        arguments = listOf(
            navArgument(name = "id") {
            type = NavType.IntType
            defaultValue = 0
        })
    )
    { PostScreen() }
}