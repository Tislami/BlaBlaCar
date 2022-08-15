package com.zeroone.blablacar.ui.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BBCCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    contentColor : Color = MaterialTheme.colors.onSurface,
    backgroundColor : Color=  MaterialTheme.colors.surface,
    content: @Composable () -> Unit,
    ) {
    Card(
        modifier = modifier
            .padding(vertical = 4.dp)
            .shadow(
                4.dp,
                MaterialTheme.shapes.medium,
                true,
                spotColor = MaterialTheme.colors.primary
            )
            .clickable { onClick() },
        contentColor = contentColor,
        backgroundColor = backgroundColor,
        content = content
    )
}