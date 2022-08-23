package com.zeroone.blablacar.presentation.ui.cards

import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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