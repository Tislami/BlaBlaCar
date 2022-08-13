package com.zeroone.blablacar.ui.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun BBCTextButton(
    modifier: Modifier= Modifier,
    text: String,
    enabled: Boolean=true,
    onClick: () -> Unit,
) {
    TextButton(
        modifier=modifier,
        onClick = onClick,
        enabled=enabled,
    ) {
        BBCText(text = text)
    }
}