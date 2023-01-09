package com.zeroone.blablacar.presentation.screens.new_post.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.LatLng
import com.zeroone.blablacar.presentation.ui.components.AutocompleteTextField



@Composable
fun LocationComponent(
    modifier: Modifier = Modifier,
    title: String,
    suggestions: List<String>,
    textFieldValue: String,
    textFieldLabelText: String,
    onTextFieldValueChange: (String) -> Unit,
    onTextFieldDone: (String) -> Unit,
    isAutocompleteLoading: Boolean,
    onMapLongClick: (LatLng) -> Unit,
    location: LatLng?
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
    ) {

        Text(text = title)

        AutocompleteTextField(
            value = textFieldValue,
            onValueChange = onTextFieldValueChange,
            labelText = textFieldLabelText,
            suggestions = suggestions,
            onSuggestionSelect = onTextFieldValueChange,
            isLoading = isAutocompleteLoading,
            onDone = onTextFieldDone,
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