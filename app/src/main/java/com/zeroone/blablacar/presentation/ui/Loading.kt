package com.zeroone.blablacar.presentation.ui

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.unit.Dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Loading(isLoading: Boolean) {

    val visibleState = MutableTransitionState(false).apply {
        targetState = true
    }

    val enterTransition: EnterTransition = scaleIn(
        animationSpec = tween(1000),
        0.25f,
        TransformOrigin.Center
    )


    AnimatedVisibility(visibleState = visibleState, enter = enterTransition) {
        if (isLoading)
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Log.d("ViewModel", "LoginScreen: Loading")
                CircularProgressIndicator()
            }
    }
}

@Composable
fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}