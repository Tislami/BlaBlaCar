package com.zeroone.blablacar.presentation.screens.new_post.from

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zeroone.blablacar.R
import com.zeroone.blablacar.domain.model.google_map.direction.Route
import com.zeroone.blablacar.presentation.screens.new_post.*
import com.zeroone.blablacar.presentation.screens.new_post.NewPostTopAppBar
import com.zeroone.blablacar.presentation.screens.new_post.components.GoogleMapView
import com.zeroone.blablacar.presentation.screens.new_post.navigation.NewPostRoutes
import com.zeroone.blablacar.presentation.ui.Loading
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DirectionRoute(
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
        var waypoints = ""
        postState.waypoints.onEach { waypoints += "place_id:${it.key}|" }
        googleMapsApiViewModel.getDirection(
            postState.toLocation.value,
            postState.fromLocation.value,
            waypoints
        )
        googleMapsApiViewModel.eventFlow.collectLatest { event->
            when(event){
                is GoogleMapsApiViewModel.GoogleMapsApiUiEvent.Error -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    DirectionScreen(
        modifier = modifier,
        scaffoldState = scaffoldState,
        postState = postState,
        mapState = mapState,
        mapsLoadingState = mapLoadingState,
        onNavigationClick = { navController.popBackStack() },
        onActionClick = { navController.navigate(NewPostRoutes.DateTime.route) },
        onRouteSelect = newPostViewModel::setCurrentRoute,
        onAddCityClick = { navController.navigate(NewPostRoutes.AddLocation.route) },
        onInfoWindowClick = {
            newPostViewModel.removeReferenceLocation(it)
            navController.navigate(NewPostRoutes.Direction.route)
        }
    )

}


@Composable
private fun DirectionScreen(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState,
    postState: NewPostState,
    mapState: GoogleMapsApiState,
    mapsLoadingState: MapsLoadingState,
    onNavigationClick: () -> Unit,
    onActionClick: () -> Unit,
    onRouteSelect: (Route) -> Unit,
    onAddCityClick: () -> Unit,
    onInfoWindowClick: (String) -> Unit,
) {

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            NewPostTopAppBar(
                navigationIcon = Icons.Default.ArrowBack,
                actionButtonIcon = Icons.Default.ArrowForward,
                onNavigationClick = onNavigationClick,
                onActionButtonClick = onActionClick,
                actionButtonVisible = postState.currentRoute != null,
                isLoading = mapsLoadingState.direction
            )
        },
        content = { padding ->
            if (mapsLoadingState.direction) {
                Loading()
            } else {
                DirectionContent(
                    modifier = modifier.padding(padding),
                    postState = postState,
                    mapsState = mapState,
                    onRouteSelect = onRouteSelect,
                    onAddCityClick = onAddCityClick,
                    onInfoWindowClick = onInfoWindowClick
                )
            }
        }
    )
}

@Composable
fun DirectionContent(
    modifier: Modifier = Modifier,
    postState: NewPostState,
    mapsState: GoogleMapsApiState,
    onRouteSelect: (Route) -> Unit,
    onAddCityClick: () -> Unit,
    onInfoWindowClick: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
    ) {
        Surface(
            modifier = Modifier
                .weight(1f)
                .heightIn(min = 500.dp),
            shape = MaterialTheme.shapes.medium,
        ) {
            GoogleMapView(
                origin = postState.fromLocation.value,
                destination = postState.toLocation.value,
                waypoints = postState.waypoints,
                routes = mapsState.direction?.routes,
                currentRoute = postState.currentRoute,
                onPolyLineOnClick = onRouteSelect,
                onInfoWindowClick = onInfoWindowClick
            )
        }
        Column {
            Surface(
                modifier = Modifier
                    .padding(vertical = 16.dp),
                elevation = 8.dp,
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colors.background
            ) {
                LazyColumn {
                    if (mapsState.direction != null) {
                        items(mapsState.direction.routes) { route ->
                            RouteItem(
                                route = route,
                                postState = postState,
                                mapsApiState = mapsState,
                                onRouteSelect = onRouteSelect
                            )
                        }
                    }
                }
            }

            Text(
                modifier = Modifier
                    .clickable { onAddCityClick() }
                    .padding(end = 16.dp, bottom = 16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Right,
                text = stringResource(id = R.string.click_for_add_city),
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.button,
            )
        }
    }
}

@Composable
private fun RouteItem(
    route: Route,
    postState: NewPostState,
    mapsApiState: GoogleMapsApiState,
    onRouteSelect: (Route) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = route == postState.currentRoute,
            onClick = { onRouteSelect(route) })
        Column {
            Text(text = route.summary)
            route.legs.onEach { leg ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp, end = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = leg.distance.text,
                        color = MaterialTheme.colors.onSurface,
                        style = MaterialTheme.typography.button,
                    )
                    Text(
                        text = leg.duration.text,
                        color = MaterialTheme.colors.onSurface,
                        style = MaterialTheme.typography.button,
                    )
                }
            }
            if (mapsApiState.waypoints.isNotEmpty()) {
                var distanceValue = 0
                var durationValue = 0
                route.legs.onEach { leg ->
                    distanceValue += leg.distance.value
                    durationValue += leg.duration.value
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
    Divider()
}
