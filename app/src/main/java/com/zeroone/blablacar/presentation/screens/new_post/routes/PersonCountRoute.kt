package com.zeroone.blablacar.presentation.screens.new_post.routes

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.zeroone.blablacar.presentation.screens.new_post.NewPostState
import com.zeroone.blablacar.presentation.screens.new_post.NewPostTopAppBar
import com.zeroone.blablacar.presentation.screens.new_post.NewPostViewModel
import com.zeroone.blablacar.presentation.screens.new_post.components.SetContent
import com.zeroone.blablacar.presentation.screens.new_post.navigation.NewPostRoutes

@Composable
fun PersonCountRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    newPostViewModel: NewPostViewModel,
) {
    val scaffoldState = rememberScaffoldState()
    val postState = newPostViewModel.newPostState.value

    PersonCountScreen(
        modifier = modifier,
        scaffoldState = scaffoldState,
        postState = postState,
        onNavigationClick = { navController.popBackStack() },
        onActionClick = { navController.navigate(NewPostRoutes.Price.route) },
        onSetPersonCount = newPostViewModel::setPersonCount,
    )
}

@Composable
private fun PersonCountScreen(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState,
    postState: NewPostState,
    onNavigationClick: () -> Unit,
    onActionClick: () -> Unit,
    onSetPersonCount: (Int) -> Unit,
) {

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            NewPostTopAppBar(
                navigationIcon = Icons.Default.ArrowBack,
                actionButtonIcon = Icons.Default.ArrowForward,
                onNavigationClick = onNavigationClick,
                onActionButtonClick = onActionClick,
                actionButtonVisible = postState.personCount != 0,
                isLoading = false
            )
        },
        content = { padding ->
            SetContent(
                modifier = modifier.padding(padding),
                title="${postState.fromLocation.text} -> ${postState.toLocation.text} arası kaç yolcu alacaksın ?",
                value = postState.personCount.toString(),
                onSetClick = onSetPersonCount,
            )
        }
    )

}