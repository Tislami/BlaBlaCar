package com.zeroone.blablacar.presentation.screens.auth.login.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.zeroone.blablacar.R

@Composable
fun LoginTextField(
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colors.onBackground.copy(alpha = .25f),
            backgroundColor = MaterialTheme.colors.onSecondary.copy(alpha = .25f),
            disabledBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
        ),

        label = {
            Text(
                text = labelText,
                color = MaterialTheme.colors.onBackground.copy(alpha = .5f),
                style = MaterialTheme.typography.button
            )
        },

        )
}