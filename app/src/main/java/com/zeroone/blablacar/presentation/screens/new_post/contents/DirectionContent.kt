package com.zeroone.blablacar.presentation.screens.new_post.contents

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.LatLng
import com.zeroone.blablacar.R
import com.zeroone.blablacar.domain.model.google_map.direction.Route
import com.zeroone.blablacar.presentation.screens.new_post.NewPostLoadingState
import com.zeroone.blablacar.presentation.screens.new_post.NewPostState
import com.zeroone.blablacar.presentation.ui.components.BBCTextButton


@Composable
fun DirectionContent(
    modifier: Modifier = Modifier,
    newPostState: NewPostState,
    newPostLoadingState: NewPostLoadingState,
    onRouteSelect: (Route) -> Unit,
    onAddCityClick: () -> Unit,
    getDirection: () -> Unit,
) {
    LaunchedEffect(key1 = true) { getDirection() }

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
                newPostState = newPostState,
                onPolyLineOnClick = onRouteSelect
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
                    if (newPostState.direction != null) {
                        items(newPostState.direction.routes) { route ->
                            RouteItem(route, newPostState, onRouteSelect)
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
    newPostState: NewPostState,
    onRouteSelect: (Route) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = route == newPostState.currentRoute,
            onClick = { onRouteSelect(route) })
        Column {
            Text(text = route.summary)
            route.legs.onEach { leg ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top=4.dp,end = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {
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
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
    Divider()
}

