package com.zeroone.blablacar.presentation.screens.post.navigation

import android.util.Log
import androidx.compose.animation.*
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import com.zeroone.blablacar.presentation.screens.main.Screen
import com.zeroone.blablacar.presentation.screens.post.PostScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.postGraph(
) {
    Log.d("Graph", "postGraph: ")
    composable(
        route = Screen.Post.route + "/{id}",
        arguments = listOf(navArgument(name = "id") {
            type = NavType.IntType
            defaultValue = 0
        })
    )
    { PostScreen() }
}