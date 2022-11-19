package com.zeroone.blablacar.presentation.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun BBCPrimaryButton(
    modifier :Modifier = Modifier,
    text:String,
    onClick : ()-> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(top = 8.dp)
            .height(50.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Text(text = text)
    }
}