package com.zeroone.blablacar.presentation.screens.home.components

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.zeroone.blablacar.R
import com.zeroone.blablacar.presentation.ui.components.BBCGreetingText
import com.zeroone.blablacar.presentation.ui.theme.LocalDimensions
import kotlinx.coroutines.launch

@Composable
internal fun HomeTopAppBar(
    showButton: Boolean,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalDimensions.current.screenHorizontalPadding),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BBCGreetingText(stringResource(id = R.string.search_for_a_trip))

        AnimatedVisibility(
            visible = showButton,
            enter = slideInVertically(
                animationSpec = tween(1000),
                initialOffsetY = { 150 },
            ),
            exit = fadeOut() + slideOutVertically(targetOffsetY = { 150 })
        ) {
            Button(
                onClick = onClick,
                modifier = Modifier.height(50.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = stringResource(id = R.string.search),
                    color = MaterialTheme.colors.onPrimary,
                    style = MaterialTheme.typography.button
                )
            }
        }
    }
}