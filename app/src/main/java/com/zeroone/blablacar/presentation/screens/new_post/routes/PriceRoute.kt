package com.zeroone.blablacar.presentation.screens.new_post.routes

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.zeroone.blablacar.R
import com.zeroone.blablacar.presentation.screens.main.Routes
import com.zeroone.blablacar.presentation.screens.new_post.NewPostState
import com.zeroone.blablacar.presentation.screens.new_post.NewPostTopAppBar
import com.zeroone.blablacar.presentation.screens.new_post.NewPostViewModel
import com.zeroone.blablacar.presentation.screens.new_post.components.SetContent
import com.zeroone.blablacar.presentation.ui.Loading
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PriceRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    newPostViewModel: NewPostViewModel,
) {
    val scaffoldState = rememberScaffoldState()
    val postState = newPostViewModel.newPostState.value

    LaunchedEffect(key1 = Unit){
        newPostViewModel.eventFlow.collectLatest { event->
            when(event){
                is NewPostViewModel.NewPostUiEvent.Error -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
                NewPostViewModel.NewPostUiEvent.PostAdded -> {
                    navController.navigate(Routes.Home.route)
                }
            }
        }
    }

    if (newPostViewModel.isLoading.value){
        Loading()
    }
    else {
        PriceScreen(
            modifier = modifier,
            scaffoldState = scaffoldState,
            postState = postState,
            onNavigationClick = { navController.popBackStack() },
            onActionClick = newPostViewModel::addPost,
            onSetPrice = newPostViewModel::setPrice,
        )
    }
}

@Composable
private fun PriceScreen(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState,
    postState: NewPostState,
    onNavigationClick: () -> Unit,
    onActionClick: () -> Unit,
    onSetPrice: (Int) -> Unit,
) {

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            NewPostTopAppBar(
                navigationIcon = Icons.Default.ArrowBack,
                actionButtonIcon = Icons.Default.ArrowForward,
                onNavigationClick = onNavigationClick,
                onActionButtonClick = onActionClick,
                actionButtonVisible = postState.price != 0,
                isLoading = false
            )
        },
        content = { padding ->
            SetContent(
                modifier = modifier.padding(padding),
                title="${postState.fromLocation.text} -> ${postState.toLocation.text} arası bir yolcu ücreti ne kadar ?",
                image = painterResource(id = R.drawable.manat),
                value = postState.price.toString(),
                onSetClick = onSetPrice,
            )
        }
    )

}

