package com.zeroone.blablacar.presentation.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun BBCTextButton(
    modifier: Modifier=Modifier,
    text: String,
    onClick: () -> Unit,
) {
    TextButton(
        modifier=modifier,
        onClick = onClick) {
        Text(text = text)
    }
}