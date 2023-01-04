package com.zeroone.blablacar.presentation.screens.posts.contents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Polyline
import com.zeroone.blablacar.domain.model.google_map.direction.OverviewPolyline


@Composable
fun DirectionContent(
    modifier: Modifier = Modifier,
    fromLocation: LatLng?,
    toLocation: LatLng?,
    points: List<List<LatLng>>?,
    onPolyLineOnClick : (List<LatLng>)-> Unit,
    ) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
    ) {

        Surface(
            modifier = Modifier.padding(vertical = 16.dp),
            shape = MaterialTheme.shapes.medium,
        ) {
            GoogleMapView(
                fromLocation =fromLocation,
                toLocation =toLocation,
                points = points,
                onPolyLineOnClick = onPolyLineOnClick
            )

            LazyColumn{

            }
        }
    }
}
