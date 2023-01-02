package com.zeroone.blablacar.presentation.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.*
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize

@Composable
fun AutocompleteTextField(
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    suggestions: List<String>,
    onSuggestionSelect: (String) -> Unit,
    onDone: (String) -> Unit,
) {
    val heightTextFields by remember { mutableStateOf(55.dp) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var expanded by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val focusState = remember { mutableStateOf(false) }


    LaunchedEffect(key1 = suggestions){
        if (suggestions.isNotEmpty() && focusState.value){
            expanded = true
        }
    }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .height(heightTextFields)
            .focusRequester(focusRequester)
            .onFocusChanged {
                focusState.value = it.isFocused
                if (!it.hasFocus) {
                    expanded=false
                    onDone(value)
                }
            }
            .onGloballyPositioned { coordinates ->
                textFieldSize = coordinates.size.toSize()
            },
        value = value,
        onValueChange = {
            onValueChange(it)
            expanded = true
        },
        label = {
            Text(
                text = labelText,
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.button,
                textAlign = TextAlign.Center,
            )
        },
        trailingIcon = {
            if (focusState.value && value.isNotEmpty()){
                IconButton(onClick = { onValueChange("") }) {
                    Icon(imageVector = Icons.Default.Close,
                        contentDescription = "Clear")
                }
            }
        },
        shape = MaterialTheme.shapes.medium,
        textStyle = MaterialTheme.typography.button,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colors.onBackground.copy(alpha = .75f),
            backgroundColor = MaterialTheme.colors.surface,
            disabledBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            expanded = false
            focusManager.clearFocus()
            onDone(value)
        }),
        singleLine = true,
    )

    AnimatedVisibility(visible = expanded) {
        Card(
            modifier = Modifier
                .padding(vertical = 1.dp, horizontal = 5.dp)
                .width(textFieldSize.width.dp),
            elevation = 15.dp,
            shape = MaterialTheme.shapes.medium
        ) {
            LazyColumn(
                modifier = Modifier.heightIn(max = 150.dp),
            ) {
                items(suggestions) {
                    SuggestionItem(
                        title = it,
                        onSelect = { title ->
                            onSuggestionSelect(title)
                            focusManager.clearFocus()
                            expanded = false
                        }
                    )
                    Divider()
                }
            }
        }
    }
}


@Composable
fun SuggestionItem(
    title: String,
    onSelect: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSelect(title)
            }
            .padding(10.dp)
    ) {
        Text(text = title, fontSize = 16.sp)
    }

}