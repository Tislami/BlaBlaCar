package com.zeroone.blablacar.presentation.screens.new_post.from

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.model.LatLng
import com.zeroone.blablacar.R
import com.zeroone.blablacar.presentation.screens.new_post.*
import com.zeroone.blablacar.presentation.screens.new_post.NewPostTopAppBar
import com.zeroone.blablacar.presentation.screens.new_post.components.LocationComponent
import com.zeroone.blablacar.presentation.screens.new_post.navigation.NewPostRoutes
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ToRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    newPostViewModel: NewPostViewModel,
    googleMapsApiViewModel: GoogleMapsApiViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val mapState: GoogleMapsApiState = googleMapsApiViewModel.mapState.value
    val mapLoadingState = googleMapsApiViewModel.mapLoadingState.value
    val postState = newPostViewModel.newPostState.value

    LaunchedEffect(key1 = mapState){
        googleMapsApiViewModel.eventFlow.collectLatest { event->
            when(event){
                is GoogleMapsApiViewModel.GoogleMapsApiUiEvent.Error -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    ToScreen(
        modifier = modifier,
        scaffoldState = scaffoldState,
        postState = postState,
        mapState = mapState,
        mapLoadingState = mapLoadingState,
        onNavigationClick = { navController.popBackStack() },
        onActionClick = {
            newPostViewModel.setToLocation(locationState = mapState.currentLocation)
            navController.navigate(NewPostRoutes.Direction.route)
        },
        textFieldValueChange = {
            googleMapsApiViewModel.autocomplete(it)
            newPostViewModel.setToLocationText(it)
        },
        textFieldDone = {
            googleMapsApiViewModel.getLocation(mapState.suggestions[it])
        },
        onMapLongClick = googleMapsApiViewModel::getReverseLocation,
    )
}


@Composable
private fun ToScreen(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState,
    postState: NewPostState,
    mapState: GoogleMapsApiState,
    mapLoadingState: MapsLoadingState,
    onNavigationClick: () -> Unit,
    onActionClick: () -> Unit,
    textFieldValueChange: (String) -> Unit,
    textFieldDone: (String) -> Unit,
    onMapLongClick: (LatLng) -> Unit,
) {

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            NewPostTopAppBar(
                navigationIcon = Icons.Default.ArrowBack,
                actionButtonIcon = Icons.Default.ArrowForward,
                onNavigationClick = onNavigationClick,
                onActionButtonClick = onActionClick,
                actionButtonVisible = mapState.currentLocation.value != null,
                isLoading = mapLoadingState.location
            )
        },
        content = { padding ->
            LocationComponent(
                modifier = modifier.padding(padding),
                title = stringResource(id = R.string.where_are_you_going_to),
                suggestions = mapState.suggestions.map { it.key },
                textFieldValue = postState.toLocation.text,
                textFieldLabelText = stringResource(id = R.string.to),
                onTextFieldValueChange = textFieldValueChange,
                onTextFieldDone = textFieldDone,
                isAutocompleteLoading = mapLoadingState.autocomplete,
                onMapLongClick = onMapLongClick,
                location = mapState.currentLocation.value
            )
        }
    )
}