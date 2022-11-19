package com.zeroone.blablacar.presentation.screens.main

import javax.annotation.concurrent.Immutable

sealed class Screen(val route: String) {


    @Immutable
    object Home : Screen("home")

    @Immutable
    object ExpandedPost : Screen("expandedPost")

    @Immutable
    object Profile : Screen("profile")

    @Immutable
    object NewPost : Screen("newPost")

    @Immutable
    object Intro : Screen("intro")

    @Immutable
    object EditProfile : Screen("editProfile")
}
