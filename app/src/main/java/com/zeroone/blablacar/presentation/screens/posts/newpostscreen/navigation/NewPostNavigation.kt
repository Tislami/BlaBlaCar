package com.zeroone.blablacar.presentation.screens.posts.newpostscreen.navigation/*
package com.zeroone.blablacar.presentation.screens.posts.newpostscreen.navigation

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.zeroone.blablacar.presentation.screens.main.Screen
import com.zeroone.blablacar.presentation.screens.posts.newpostscreen.NewPostScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.newPostGraph(
    modifier: Modifier = Modifier,
    backOnClick:()->Unit,
    shareClick:() -> Unit
) {
    Log.d("Graph", "homeGraph: ")
    composable(route = Screen.NewPost.route) {
        NewPostScreen(
            modifier= modifier,
            backOnClick = backOnClick,
            shareClick = shareClick,
        )
    }
}*/
