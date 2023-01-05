package com.zeroone.blablacar.presentation.screens.posts.contents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.LatLng
import com.zeroone.blablacar.domain.model.google_map.direction.Direction


@Composable
fun DirectionContent(
    modifier: Modifier = Modifier,
    fromLocation: LatLng?,
    toLocation: LatLng?,
    direction: Direction? = null,
    onPolyLineOnClick: (List<LatLng>) -> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
    ) {
        Surface(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .weight(1f),
            shape = MaterialTheme.shapes.medium,
        ) {
            GoogleMapView(
                fromLocation = fromLocation,
                toLocation = toLocation,
                direction = direction,
                onPolyLineOnClick = onPolyLineOnClick
            )
        }
        Surface(
            modifier = Modifier
                .padding(vertical = 16.dp),
            elevation = 8.dp,
            shape = MaterialTheme.shapes.medium,
        ) {
            LazyColumn {
                if (direction != null) {
                    items(direction.routes) {
                        IconToggleButton(checked = true, onCheckedChange = {}) {
                            Text(text = it.summary)
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}
