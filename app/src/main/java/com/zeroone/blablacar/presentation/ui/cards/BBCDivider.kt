package com.zeroone.blablacar.presentation.ui.cards

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BBCDivider() {
    Divider(
        thickness = .5.dp,
        color = Color.Black,
        modifier = Modifier.padding(vertical = 16.dp)
    )
}