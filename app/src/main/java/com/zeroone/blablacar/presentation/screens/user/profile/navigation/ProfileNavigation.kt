package com.zeroone.blablacar.presentation.screens.user.profile.navigation

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.zeroone.blablacar.domain.model.defaultPost
import com.zeroone.blablacar.presentation.screens.main.Screen
import com.zeroone.blablacar.presentation.screens.user.profile.ProfileScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.profileGraph(
    backOnClick:()->Unit,
    settingOnClick:()->Unit,
    ) {
    Log.d("Graph", "profileGraph: ")
    composable(route = Screen.Profile.route) {
        ProfileScreen(
            user= defaultPost.user,
            backOnClick = backOnClick,
            settingOnClick = settingOnClick
        )
    }
}