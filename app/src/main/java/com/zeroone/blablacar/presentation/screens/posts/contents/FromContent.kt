package com.zeroone.blablacar.presentation.screens.posts.contents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.LatLng
import com.zeroone.blablacar.presentation.ui.components.AutocompleteTextField


@Composable
fun FromContent(
    modifier: Modifier = Modifier,
    suggestions: List<String>,
    textFieldValue: String,
    textFieldLabelText: String,
    onTextFieldValueChange: (String) -> Unit,
    onTextFieldDone: (String) -> Unit,
    onMapLongClick: (LatLng) -> Unit,
    location: LatLng?
) {

    val visibleState = remember {
        MutableTransitionState(initialState = false).apply {
            targetState = true
        }
    }

    val enterTransition = slideInVertically(
        animationSpec = tween(1000),
        initialOffsetY = { 2000 }
    )


    AnimatedVisibility(
        visibleState = visibleState,
        enter = enterTransition
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
        ) {

            Text(text = stringResource(id = com.zeroone.blablacar.R.string.where_are_you_going_from))

            AutocompleteTextField(
                value = textFieldValue,
                onValueChange = onTextFieldValueChange,
                labelText = textFieldLabelText,
                suggestions = suggestions,
                onSuggestionSelect = onTextFieldValueChange,
                onDone = onTextFieldDone
            )
            Surface(
                modifier = Modifier.padding(vertical = 16.dp),
                shape = MaterialTheme.shapes.medium,
            ) {
                GoogleMapView(
                    location = location,
                    onMapLongClick = onMapLongClick
                )
            }
        }
    }
}