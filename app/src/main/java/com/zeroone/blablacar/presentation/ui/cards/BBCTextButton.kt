package com.zeroone.blablacar.presentation.ui.cards

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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