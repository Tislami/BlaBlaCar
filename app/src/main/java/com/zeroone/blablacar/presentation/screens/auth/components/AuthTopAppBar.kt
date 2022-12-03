package com.zeroone.blablacar.presentation.screens.auth.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zeroone.blablacar.R

@Composable
internal fun AuthTopAppBar(
    buttonIcon: ImageVector,
    onClick: () -> Unit) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
        ) {
            Icon(
                imageVector = buttonIcon,
                contentDescription = stringResource(id = R.string.close),
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}