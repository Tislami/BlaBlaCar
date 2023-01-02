package com.zeroone.blablacar.presentation.screens.posts


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.zeroone.blablacar.R
import com.zeroone.blablacar.presentation.screens.main.Routes
import com.zeroone.blablacar.presentation.screens.posts.google_maps.GoogleMapsApiState
import com.zeroone.blablacar.presentation.screens.posts.google_maps.GoogleMapsApiViewModel
import com.zeroone.blablacar.presentation.ui.components.AutocompleteTextField
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NewPostRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    googleMapsApiViewModel: GoogleMapsApiViewModel = hiltViewModel(),
    newPostViewModel: NewPostViewModel = hiltViewModel(),
) {

    NewPostScreen(
        modifier = modifier,
        googleMapsApiViewModel = googleMapsApiViewModel,
        newPostViewModel = newPostViewModel,
        closeOnClick = { navController.navigate(Routes.Home.route) }
    )
}

@Composable
private fun NewPostScreen(
    modifier: Modifier = Modifier,
    googleMapsApiViewModel: GoogleMapsApiViewModel,
    newPostViewModel: NewPostViewModel,
    closeOnClick: () -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
    val googleMapsApiState = googleMapsApiViewModel.googleMapsApiState.value
    val newPostState = newPostViewModel.newPostState.value

    LaunchedEffect(key1 = true) {
        googleMapsApiViewModel.eventFlow.collectLatest { event ->
            when (event) {
                is GoogleMapsApiViewModel.GeoPlaceUiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            NewPostTopAppBar(
                buttonIcon = Icons.Default.Close,
                onClick = closeOnClick,
            )
        },
        content = { innerPadding ->
            NewPostContent(
                modifier = modifier.padding(innerPadding),
                googleMapsApiState = googleMapsApiState,
                newPostState = newPostState,
                onFromLocationValueChange = {
                    newPostViewModel.setFromLocation(it)
                    googleMapsApiViewModel.autocomplete(it)
                },
                onToLocationValueChange = {
                    newPostViewModel.setToLocation(it)
                    googleMapsApiViewModel.autocomplete(it)
                },
                onFromDone = { googleMapsApiViewModel.getLocation(
                    googleMapsApiState.suggestions[it]
                ) },
                onToDone = { googleMapsApiViewModel.getLocation(
                    googleMapsApiState.suggestions[it]
                ) },
                onMapLongClick = googleMapsApiViewModel::getReverseLocation
            )
        }
    )
}


@Composable
private fun NewPostContent(
    modifier: Modifier = Modifier,
    googleMapsApiState: GoogleMapsApiState,
    newPostState: NewPostState,
    onFromLocationValueChange: (String) -> Unit,
    onToLocationValueChange: (String) -> Unit,
    onFromDone: (String) -> Unit,
    onToDone: (String) -> Unit,
    onMapLongClick: (LatLng)->Unit
    ) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
    ) {
        AutocompleteTextField(
            value = newPostState.fromLocation,
            onValueChange = onFromLocationValueChange,
            labelText = stringResource(id = R.string.from),
            suggestions = googleMapsApiState.suggestions.map { it.key },
            onSuggestionSelect = onFromLocationValueChange,
            onDone = onFromDone
        )

        AutocompleteTextField(
            value = newPostState.toLocation,
            onValueChange = onToLocationValueChange,
            labelText = stringResource(id = R.string.to),
            suggestions = googleMapsApiState.suggestions.map { it.key },
            onSuggestionSelect = onToLocationValueChange,
            onDone = onToDone
        )

        Surface(
            modifier = Modifier.padding(vertical = 16.dp),
            shape = MaterialTheme.shapes.medium,
        ) {
            GoogleMapView(
                googleMapsApiState = googleMapsApiState,
                onMapLongClick = onMapLongClick
            )
        }
    }
}


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun GoogleMapView(
    googleMapsApiState : GoogleMapsApiState,
    onMapLongClick: (LatLng)->Unit
) {

    val baku = LatLng(40.40144780549906,49.85737692564726)
    val cameraPositionState = rememberCameraPositionState{ position = CameraPosition.fromLatLngZoom(baku,6f) }
    val mapProperties by remember { mutableStateOf(MapProperties(mapType = MapType.NORMAL)) }
    val uiSettings by remember { mutableStateOf(MapUiSettings(compassEnabled = false)) }
    val fromMarkerState = rememberMarkerState()
    val toMarkerState = rememberMarkerState()

    LaunchedEffect(key1 = googleMapsApiState.fromLocation){
        if (googleMapsApiState.fromLocation!=null){
            fromMarkerState.position = googleMapsApiState.fromLocation
            cameraPositionState.position = CameraPosition.fromLatLngZoom(googleMapsApiState.fromLocation,15f)
        }
    }

    LaunchedEffect(key1 = googleMapsApiState.toLocation){
        if (googleMapsApiState.toLocation!=null){
            toMarkerState.position = googleMapsApiState.toLocation
            cameraPositionState.position = CameraPosition.fromLatLngZoom(googleMapsApiState.toLocation,15f)
        }
    }

    GoogleMap(
        modifier = Modifier,
        cameraPositionState = cameraPositionState,
        properties = mapProperties,
        uiSettings = uiSettings,
        onMapLongClick = {
            onMapLongClick(it)
            fromMarkerState.position = it
            cameraPositionState.position = CameraPosition.fromLatLngZoom(it,15f)
        }
    ) {

        Marker(state =fromMarkerState)
        Marker(state =toMarkerState)

        Polyline(
            points = googleMapsApiState.polyLinesPoints,
            color = MaterialTheme.colors.primary,
        )
    }
}
