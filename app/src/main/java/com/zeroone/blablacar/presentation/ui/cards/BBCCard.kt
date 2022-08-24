package com.zeroone.blablacar.presentation.ui.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BBCCard(
    modifier: Modifier = Modifier,
    contentColor : Color = MaterialTheme.colors.onBackground,
    backgroundColor : Color=  MaterialTheme.colors.background,
    content: @Composable () -> Unit,
    ) {
    Card(
        modifier = modifier.padding(vertical = 4.dp),
        shape = MaterialTheme.shapes.medium,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        border = BorderStroke(.5.dp,MaterialTheme.colors.primary),
        elevation = 4.dp,
        content = content
    )
}