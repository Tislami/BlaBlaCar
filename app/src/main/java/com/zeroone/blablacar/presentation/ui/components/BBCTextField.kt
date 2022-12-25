package com.zeroone.blablacar.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign

@Composable
fun BBCTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    trailingOnclick: (() -> Unit)? = null,
    trailingIcon: ImageVector? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        textStyle = MaterialTheme.typography.button,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colors.onBackground.copy(alpha = .75f),
            backgroundColor = MaterialTheme.colors.surface,
            disabledBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
        ),
        label = {
            Text(
                text = labelText,
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.button,
                textAlign = TextAlign.Center,
                )
        },
        trailingIcon = {
            if (trailingOnclick!=null && trailingIcon!=null)
            IconButton(onClick = trailingOnclick ) {
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary
                )
            }
        }
    )
}