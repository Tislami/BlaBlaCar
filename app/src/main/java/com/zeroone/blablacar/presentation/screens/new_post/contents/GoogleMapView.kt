package com.zeroone.blablacar.presentation.screens.new_post.contents

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.android.gms.maps.model.*
import com.google.maps.android.compose.*
import com.zeroone.blablacar.domain.model.google_map.direction.Route
import com.zeroone.blablacar.presentation.screens.new_post.NewPostLoadingState
import com.zeroone.blablacar.presentation.screens.new_post.NewPostState
import com.zeroone.blablacar.utils.decodePoly


@Composable
fun GoogleMapView(
    location: LatLng?,
    onMapLongClick: (LatLng) -> Unit,
) {
    val baku = LatLng(40.40144780549906, 49.85737692564726)
    val cameraPositionState =
        rememberCameraPositionState { position = CameraPosition.fromLatLngZoom(baku, 6f) }
    val mapProperties by remember { mutableStateOf(MapProperties(mapType = MapType.NORMAL)) }
    val uiSettings by remember { mutableStateOf(MapUiSettings(compassEnabled = false)) }
    val markerState = rememberMarkerState()

    LaunchedEffect(key1 = location) {
        if (location != null) {
            markerState.position = location
            cameraPositionState.position = CameraPosition.fromLatLngZoom(location, 15f)
        }
    }

    GoogleMap(
        modifier = Modifier,
        cameraPositionState = cameraPositionState,
        properties = mapProperties,
        uiSettings = uiSettings,
        onMapLongClick = {
            onMapLongClick(it)
            markerState.position = it
            cameraPositionState.position = CameraPosition.fromLatLngZoom(it, 15f)
        }
    ) {
        Marker(state = markerState)
    }
}

@Composable
fun GoogleMapView(
    newPostState: NewPostState,
    onPolyLineOnClick: (Route) -> Unit,
) {
    if (newPostState.direction != null && newPostState.fromLocation != null && newPostState.toLocation != null) {

        val cameraPositionState =
            rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(newPostState.fromLocation, 6f)
            }
        val mapProperties by remember { mutableStateOf(MapProperties(mapType = MapType.NORMAL)) }
        val uiSettings by remember { mutableStateOf(MapUiSettings(compassEnabled = false)) }

        val fromMarkerState = rememberMarkerState(
            position = LatLng(
                newPostState.fromLocation.latitude,
                newPostState.fromLocation.longitude
            )
        )
        val toMarkerState = rememberMarkerState(
            position = LatLng(
                newPostState.toLocation.latitude,
                newPostState.toLocation.longitude
            )
        )
        GoogleMap(
            modifier = Modifier,
            cameraPositionState = cameraPositionState,
            properties = mapProperties,
            uiSettings = uiSettings,
        ) {
            Marker(state = fromMarkerState)
            Marker(state = toMarkerState)

            newPostState.waypoints.values.onEach { location ->
                if (location != null) {
                    val markerState = rememberMarkerState(
                        position = location
                    )
                    Marker(
                        state = markerState,
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE),
                    )
                }
            }

            newPostState.direction.routes.onEach { route ->
                Polyline(
                    color = if (route == newPostState.currentRoute) MaterialTheme.colors.primary else Color.Gray,
                    points = decodePoly(route.overview_polyline.points),
                    width = 15f,
                    zIndex = if (route == newPostState.currentRoute) 11.0f else 10.0f,
                    clickable = true,
                    onClick = { onPolyLineOnClick(route) }
                )
            }
        }
    }
}
