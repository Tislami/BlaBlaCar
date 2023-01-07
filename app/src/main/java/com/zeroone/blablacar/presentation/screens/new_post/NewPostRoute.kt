package com.zeroone.blablacar.presentation.screens.new_post

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zeroone.blablacar.R
import com.zeroone.blablacar.domain.model.Post2
import com.zeroone.blablacar.presentation.screens.main.Routes
import com.zeroone.blablacar.presentation.screens.new_post.contents.DateTimePeronPriceContent
import com.zeroone.blablacar.presentation.screens.new_post.contents.DirectionContent
import com.zeroone.blablacar.presentation.screens.new_post.contents.LocationContent
import com.zeroone.blablacar.presentation.ui.Loading
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
        navigateToHome = {navController.navigate(Routes.Home.route)},
        onNavigationClick = { navController.navigate(Routes.Home.route) }
    )
}

@Composable
private fun NewPostScreen(
    modifier: Modifier = Modifier,
    newPostViewModel: NewPostViewModel,
    navigateToHome : ()->Unit,
    onNavigationClick: () -> Unit,
) {
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val newPostState = newPostViewModel.newPostState.value
    val newPostLoadingState = newPostViewModel.newPostLoadingState.value
    val contentState = remember { mutableStateOf(NewPostContentState.From) }
    var onActionButtonClick by remember { mutableStateOf({ }) }
    var onNavigationButtonClick by remember { mutableStateOf({ }) }
    var actionButtonVisible by remember { mutableStateOf(false) }
    var navigationIcon by remember { mutableStateOf(Icons.Default.Close) }


    LaunchedEffect(key1 = true) {
        newPostViewModel.eventFlow.collectLatest { event ->
            when (event) {
                is NewPostViewModel.NewPostUiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
                NewPostViewModel.NewPostUiEvent.PostAdded -> {
                    Toast.makeText(context,"Post added",Toast.LENGTH_LONG).show()
                    navigateToHome()
                }
            }
        }
    }

    when (contentState.value) {
        NewPostContentState.From -> {
            onNavigationButtonClick = {
                onNavigationClick()
            }
            onActionButtonClick = {
                contentState.value = NewPostContentState.To
            }
            navigationIcon = Icons.Default.Close
            actionButtonVisible = newPostState.fromLocation != null
        }
        NewPostContentState.To -> {
            onActionButtonClick = {
                newPostViewModel.getDirection()
                contentState.value = NewPostContentState.Direction
            }
            onNavigationButtonClick = {
                contentState.value = NewPostContentState.From
            }
            navigationIcon = Icons.Default.ArrowBack
            actionButtonVisible = newPostState.toLocation != null
        }
        NewPostContentState.NewLocation -> {
            onActionButtonClick = {
                newPostViewModel.addWaypoint()
                newPostViewModel.getDirection()
                contentState.value = NewPostContentState.Direction
            }
            onNavigationButtonClick = {
                contentState.value = NewPostContentState.Direction
            }
            actionButtonVisible = newPostState.newLocation != null
        }
        NewPostContentState.Direction -> {
            actionButtonVisible = newPostState.currentRoute != null
            onActionButtonClick = {
                contentState.value = NewPostContentState.DateTimePersonPrice
            }
            onNavigationButtonClick = {
                contentState.value = NewPostContentState.To
            }
        }
        NewPostContentState.DateTimePersonPrice -> {
            onActionButtonClick = { newPostViewModel.addNewPost()}
            onNavigationButtonClick = {
                contentState.value = NewPostContentState.Direction
            }

            actionButtonVisible = (newPostState.date.isNotEmpty() &&
                    newPostState.time.isNotEmpty() &&
                    newPostState.personCount > 0 &&
                    newPostState.price > 0
                    )
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            NewPostTopAppBar(
                newPostLoadingState = newPostLoadingState,
                navigationIcon = navigationIcon,
                actionButtonIcon = Icons.Default.ArrowForward,
                onNavigationClick = onNavigationButtonClick,
                onActionButtonClick = onActionButtonClick,
                actionButtonVisible = actionButtonVisible,
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
    
    Loading(isLoading = newPostLoadingState.adLoading)
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
        NewPostContentState.DateTimePersonPrice -> {
            DateTimePeronPriceContent(
                modifier = modifier,
                newPostState = newPostState,
                setData = newPostViewModel::setDate,
                setTime = newPostViewModel::setTime,
                setPersonCount = newPostViewModel::setPersonCount,
                setPrice = newPostViewModel::setPrice,
            )
        }
    }
}


enum class NewPostContentState {
    From,
    To,
    NewLocation,
    Direction,
    DateTimePersonPrice,
}
