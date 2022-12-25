package com.zeroone.blablacar.presentation.screens.main

import javax.annotation.concurrent.Immutable

sealed class Routes(val route: String) {


    @Immutable
    object Home : Routes("home")

    @Immutable
    object ExpandedPost : Routes("expandedPost")

    @Immutable
    object Profile : Routes("profile")

    @Immutable
    object NewPost : Routes("newPost")

    @Immutable
    object Intro : Routes("intro")

    @Immutable
    object EditProfile : Routes("editProfile")
}
