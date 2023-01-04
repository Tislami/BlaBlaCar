package com.zeroone.blablacar.presentation.screens.posts

import android.util.Log
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
import com.zeroone.blablacar.presentation.screens.posts.contents.DirectionContent
import com.zeroone.blablacar.presentation.screens.posts.contents.FromContent
import com.zeroone.blablacar.presentation.screens.posts.contents.ToContent
import com.zeroone.blablacar.presentation.screens.posts.google_maps.GoogleMapsApiViewModel
import com.zeroone.blablacar.presentation.screens.posts.google_maps.LocationState
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NewPostRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    googleMapsApiViewModel: GoogleMapsApiViewModel = hiltViewModel(),
) {
    NewPostScreen(
        modifier = modifier,
        googleMapsApiViewModel = googleMapsApiViewModel,
        onNavigationClick = { navController.navigate(Routes.Home.route) }
    )
}

@Composable
private fun NewPostScreen(
    modifier: Modifier = Modifier,
    googleMapsApiViewModel: GoogleMapsApiViewModel,
    onNavigationClick: () -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
    val newPostState = googleMapsApiViewModel.newPostState.value
    val contentState = remember { mutableStateOf(NewPostContentState.From) }


    LaunchedEffect(key1 = true) {
        googleMapsApiViewModel.eventFlow.collectLatest { event ->
            when (event) {
                is GoogleMapsApiViewModel.GoogleMapsApiUiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }



    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            NewPostTopAppBar(
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
                            Log.d("NewPostTag", "NewPostScreen: " +
                                    "${newPostState.fromLocation}" +
                                    "${newPostState.toLocation}")
                        }
                    }
                },
                actionButtonVisible = newPostState.fromLocation != null
            )
        },
        content = { innerPadding ->
            when (contentState.value) {
                NewPostContentState.From -> {
                    FromContent(
                        modifier = modifier.padding(innerPadding),
                        suggestions = newPostState.suggestions.map { it.key },
                        textFieldValue = newPostState.fromLocationText,
                        textFieldLabelText = stringResource(id = R.string.from),
                        onTextFieldValueChange = {
                            googleMapsApiViewModel.setLocationTextValue(
                                value = it,
                                locationState = LocationState.From
                            )
                        },
                        onTextFieldDone = {
                            googleMapsApiViewModel.getLocation(
                                placeId = newPostState.suggestions[it],
                                locationState = LocationState.From
                            )
                        },
                        onMapLongClick = googleMapsApiViewModel::getReverseLocation,
                        location = newPostState.fromLocation
                    )
                }
                NewPostContentState.To -> {
                    ToContent(
                        modifier = modifier.padding(innerPadding),
                        suggestions = newPostState.suggestions.map { it.key },
                        textFieldValue = newPostState.toLocationText,
                        textFieldLabelText = stringResource(id = R.string.to),
                        onTextFieldValueChange = {
                            googleMapsApiViewModel.setLocationTextValue(
                                value = it,
                                locationState = LocationState.To
                            )
                        },
                        onTextFieldDone = {
                            googleMapsApiViewModel.getLocation(
                                placeId = newPostState.suggestions[it],
                                locationState = LocationState.To
                            )
                        },
                        onMapLongClick = googleMapsApiViewModel::getReverseLocation,
                        location = newPostState.toLocation
                    )
                }
                NewPostContentState.Direction -> {
                    googleMapsApiViewModel.getDirection()
                    DirectionContent(
                        modifier = modifier.padding(innerPadding),
                        fromLocation = newPostState.fromLocation,
                        toLocation = newPostState.toLocation,
                        points = newPostState.polyLinesPoints,
                        onPolyLineOnClick = googleMapsApiViewModel::getSelectedDirection
                    )
                }
            }
        }
    )
}


enum class NewPostContentState {
    From,
    To,
    Direction
}
