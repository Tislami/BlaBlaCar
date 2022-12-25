package com.zeroone.blablacar.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
internal fun BBCGreetingText(
    text : String,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = text,
        textAlign = textAlign,
        color= MaterialTheme.colors.onBackground,
        style = MaterialTheme.typography.h1,
    )
}

