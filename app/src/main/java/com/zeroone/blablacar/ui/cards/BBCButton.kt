package com.zeroone.blablacar.ui.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun BBCButton(
    modifier: Modifier= Modifier,
    text: String,
    enabled: Boolean=true,

    onClick: () -> Unit,
) {
    Button(
        modifier=modifier.paddingFromBaseline(bottom = 32.dp),
        onClick = onClick,
        enabled=enabled,
    ) {
        BBCText(text = text)
    }
}