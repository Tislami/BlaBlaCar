package com.zeroone.blablacar.presentation.screens.posts.sharedpost.navigation

import android.util.Log
import androidx.compose.animation.*
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import com.zeroone.blablacar.presentation.screens.main.Screen
import com.zeroone.blablacar.presentation.screens.posts.sharedpost.ExpandedPostScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.expandedPostGraph(
) {
    Log.d("Graph", "expandedPostGraph: ")
    composable(
        route = Screen.ExpandedPost.route + "/{id}",
        arguments = listOf(navArgument(name = "id") {
            type = NavType.IntType
            defaultValue = 0
        })
    )
    { ExpandedPostScreen() }
}