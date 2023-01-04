package com.zeroone.blablacar.presentation.screens.posts.contents

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.android.gms.maps.model.ButtCap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Polyline
import com.google.maps.android.compose.*
import com.zeroone.blablacar.domain.model.google_map.direction.Direction
import com.zeroone.blablacar.domain.model.google_map.direction.OverviewPolyline


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
    fromLocation: LatLng?,
    toLocation: LatLng?,
    points: List<List<LatLng>>? = null,
    onPolyLineOnClick : (List<LatLng>)-> Unit,
    ) {
    if (points != null && points.isNotEmpty()) {
        val center = points[points.size / 2][points.size / 2]
        val cameraPositionState =
            rememberCameraPositionState { position = CameraPosition.fromLatLngZoom(center, 6f) }
        val mapProperties by remember { mutableStateOf(MapProperties(mapType = MapType.NORMAL)) }
        val uiSettings by remember { mutableStateOf(MapUiSettings(compassEnabled = false)) }
        val width by remember { mutableStateOf(15.0f) }
        val zIndex by remember { mutableStateOf(15.0f) }

        val fromMarkerState = rememberMarkerState(
            position = LatLng(
                fromLocation!!.latitude,
                fromLocation.longitude
            )
        )
        val toMarkerState = rememberMarkerState(
            position = LatLng(
                toLocation!!.latitude,
                toLocation.longitude
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

            points.onEachIndexed { index, points ->
                Polyline(
                    color = if (index == 0) MaterialTheme.colors.primary else Color.Gray,
                    points = points,
                    endCap = ButtCap(),
                    width = width,
                    zIndex = if (index == 0) 11.0f else 10.0f,
                    clickable = true,
                    onClick = { onPolyLineOnClick(it.points) }
                )
            }
        }
    }
}