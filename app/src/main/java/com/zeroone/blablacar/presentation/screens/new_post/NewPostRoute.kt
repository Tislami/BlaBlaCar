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
                is NewPostViewModel.NewPostUiEvent.ShowSnackBar -> {
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
                        NewPostContentState.NewLocation -> {
                            newPostViewModel.addWaypoint()
                            contentState.value = NewPostContentState.Direction
                        }
                    }
                },
                actionButtonVisible = when (contentState.value) {
                    NewPostContentState.From -> {
                        newPostState.fromLocation!=null                    }
                    NewPostContentState.To -> {
                        newPostState.toLocation!=null
                    }
                    NewPostContentState.NewLocation -> {
                        newPostState.newLocation!=null
                    }
                    NewPostContentState.Direction -> {
                        newPostState.currentRoute!=null
                    }
                }
            )
        },
        content = { innerPadding ->
            NewPostContent(
                modifier = modifier.padding(innerPadding),
                contentState = contentState,
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
    contentState: MutableState<NewPostContentState>,
    newPostState: NewPostState,
    newPostLoadingState: NewPostLoadingState,
    newPostViewModel: NewPostViewModel
) {
    when (contentState.value) {
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
            DirectionContent(
                modifier = modifier,
                newPostState = newPostState,
                newPostLoadingState = newPostLoadingState,
                onRouteSelect = newPostViewModel::getSelectedDirection,
                onAddCityClick = { contentState.value = NewPostContentState.NewLocation },
                getDirection = newPostViewModel::getDirection
            )
        }
        NewPostContentState.NewLocation -> {
            LocationContent(
                modifier = modifier,
                title = stringResource(id = R.string.add_new_location),
                suggestions = newPostState.suggestions.map { it.key },
                textFieldValue = newPostState.newLocationText,
                textFieldLabelText = stringResource(id = R.string.new_location),
                onTextFieldValueChange = {
                    newPostViewModel.setLocationTextValue(
                        value = it,
                        locationState = LocationState.NewLocation
                    )
                },
                onTextFieldDone = {
                    newPostViewModel.getLocation(
                        placeId = newPostState.suggestions[it],
                        locationState = LocationState.NewLocation
                    )
                },
                newPostLoadingState = newPostLoadingState,
                onMapLongClick = newPostViewModel::getReverseLocation,
                location = newPostState.newLocation
            )
        }
    }
}


enum class NewPostContentState {
    From,
    To,
    NewLocation,
    Direction
}
