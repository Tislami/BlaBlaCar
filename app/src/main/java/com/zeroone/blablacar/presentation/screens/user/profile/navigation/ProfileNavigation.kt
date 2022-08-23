package com.zeroone.blablacar.presentation.screens.user.profile.navigation

import android.util.Log
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zeroone.blablacar.presentation.screens.Screen
import com.zeroone.blablacar.presentation.screens.auth.login.LoginScreen
import com.zeroone.blablacar.presentation.screens.home.HomeScreen
import com.zeroone.blablacar.presentation.screens.user.profile.ProfileScreen
import com.zeroone.blablacar.utils.TAG

fun NavGraphBuilder.profileGraph(
) {
    Log.d(TAG, "profileGraph: ")
    composable(route = Screen.Profile.route) {
        ProfileScreen()
    }
}