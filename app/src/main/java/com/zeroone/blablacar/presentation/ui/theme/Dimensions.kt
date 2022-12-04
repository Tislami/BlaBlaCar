package com.zeroone.blablacar.presentation.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val small: Dp = 4.dp,
    val medium: Dp = 8.dp,
    val large: Dp = 16.dp,
    val extraLarge: Dp = 16.dp,
    val screenHorizontalPadding: Dp = 24.dp,
    val profileImageSizeMin : Dp = 65.dp,
    val profileImageSize : Dp = 100.dp
)


val LocalDimensions = compositionLocalOf { Dimensions()}

