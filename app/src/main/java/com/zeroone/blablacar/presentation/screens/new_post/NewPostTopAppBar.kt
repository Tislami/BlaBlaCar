package com.zeroone.blablacar.presentation.screens.new_post

import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
internal fun NewPostTopAppBar(
    navigationIcon: ImageVector,
    actionButtonIcon: ImageVector,
    onNavigationClick: () -> Unit,
    onActionButtonClick: () -> Unit,
    actionButtonVisible: Boolean,
    isLoading: Boolean,
    ) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp,
        title = {},
        navigationIcon = {
            IconButton(
                onClick = onNavigationClick,
                modifier = Modifier
            ) {
                Icon(
                    imageVector = navigationIcon,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier.size(30.dp)
                )
            }
        },
        actions = {
            if (isLoading) {
                CircularProgressIndicator()
            }
            if (actionButtonVisible) {
                IconButton(
                    onClick = onActionButtonClick,
                    modifier = Modifier
                ) {
                    Icon(
                        imageVector = actionButtonIcon,
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    )
}