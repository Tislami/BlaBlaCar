package com.zeroone.blablacar.presentation.screens.new_post

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zeroone.blablacar.R
import com.zeroone.blablacar.presentation.screens.main.Routes
import com.zeroone.blablacar.presentation.screens.new_post.contents.DirectionContent
import com.zeroone.blablacar.presentation.screens.new_post.contents.LocationContent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NewPostRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    newPostViewModel: NewPostViewModel = hiltViewModel(),
) {
    NewPostScreen(
        modifier = modifier,
        newPostViewModel = newPostViewModel,
        onNavigationClick = { navController.navigate(Routes.Home.route) }
    )
}

@Composable
private fun NewPostScreen(
    modifier: Modifier = Modifier,
    newPostViewModel: NewPostViewModel,
    onNavigationClick: () -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
    val newPostState = newPostViewModel.newPostState.value
    val newPostLoadingState = newPostViewModel.newPostLoadingState.value
    val contentState = remember { mutableStateOf(NewPostContentState.From) }


    LaunchedEffect(key1 = true) {
        newPostViewModel.eventFlow.collectLatest { event ->
            when (event) {
                is NewPostViewModel.GoogleMapsApiUiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }



    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            NewPostTopAppBar(
                newPostLoadingState= newPostLoadingState,
                navigationIcon = Icons.Default.Close,
                actionButtonIcon = Icons.Default.ArrowForward,
                onNavigationClick = onNavigationClick,
                onActionButtonClick = {
                    when (contentState.value) {
                        NewPostContentState.From -> {
                            contentState.value = NewPostContentState.To
                        }
                        NewPostContentState.To -> {
                            contentState.value = NewPostContentState.Direction

                        }
                        NewPostContentState.Direction -> {

                        }
                    }
                },
                actionButtonVisible = when (contentState.value) {
                    NewPostContentState.From -> {
                        newPostState.fromLocation!=null                    }
                    NewPostContentState.To -> {
                        newPostState.toLocation!=null
                    }
                    else -> {false}
                }
            )
        },
        content = { innerPadding ->
            NewPostContent(
                modifier = modifier.padding(innerPadding),
                contentState = contentState.value,
                newPostState = newPostState,
                newPostLoadingState = newPostLoadingState,
                newPostViewModel = newPostViewModel
            )
        }
    )
}

@Composable
private fun NewPostContent(
    modifier: Modifier,
    contentState: NewPostContentState,
    newPostState: NewPostState,
    newPostLoadingState: NewPostLoadingState,
    newPostViewModel: NewPostViewModel
) {
    when (contentState) {
        NewPostContentState.From -> {
            LocationContent(
                modifier = modifier,
                title = stringResource(id = R.string.where_are_you_going_from),
                suggestions = newPostState.suggestions.map { it.key },
                textFieldValue = newPostState.fromLocationText,
                textFieldLabelText = stringResource(id = R.string.from),
                onTextFieldValueChange = {
                    newPostViewModel.setLocationTextValue(
                        value = it,
                        locationState = LocationState.From
                    )
                },
                onTextFieldDone = {
                    newPostViewModel.getLocation(
                        placeId = newPostState.suggestions[it],
                        locationState = LocationState.From
                    )
                },
                newPostLoadingState = newPostLoadingState,
                onMapLongClick = newPostViewModel::getReverseLocation,
                location = newPostState.fromLocation
            )
        }
        NewPostContentState.To -> {
            LocationContent(
                modifier = modifier,
                title = stringResource(id = R.string.where_are_you_going_to),
                suggestions = newPostState.suggestions.map { it.key },
                textFieldValue = newPostState.toLocationText,
                textFieldLabelText = stringResource(id = R.string.to),
                onTextFieldValueChange = {
                    newPostViewModel.setLocationTextValue(
                        value = it,
                        locationState = LocationState.To
                    )
                },
                onTextFieldDone = {
                    newPostViewModel.getLocation(
                        placeId = newPostState.suggestions[it],
                        locationState = LocationState.To
                    )
                },
                newPostLoadingState = newPostLoadingState,
                onMapLongClick = newPostViewModel::getReverseLocation,
                location = newPostState.toLocation
            )
        }
        NewPostContentState.Direction -> {
            newPostViewModel.getDirection()
            DirectionContent(
                modifier = modifier,
                newPostState = newPostState,
                onPolyLineOnClick = newPostViewModel::getSelectedDirection
            )
        }
    }
}


enum class NewPostContentState {
    From,
    To,
    Direction
}