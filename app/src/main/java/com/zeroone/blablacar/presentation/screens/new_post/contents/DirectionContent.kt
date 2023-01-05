package com.zeroone.blablacar.presentation.screens.new_post.contents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.LatLng
import com.zeroone.blablacar.presentation.screens.new_post.NewPostState


@Composable
fun DirectionContent(
    modifier: Modifier = Modifier,
    newPostState: NewPostState,
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
                fromLocation = newPostState.fromLocation,
                toLocation = newPostState.toLocation,
                direction = newPostState.direction,
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
                if (newPostState.direction != null) {
                    items(newPostState.direction.routes) {
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
