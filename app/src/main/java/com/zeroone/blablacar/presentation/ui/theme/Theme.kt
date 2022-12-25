package com.zeroone.blablacar.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Blue,
    onPrimary = White,
    background = Color.White,
    onSurface = Color.Black,
    )

private val LightColorPalette = lightColors(
    primary = Blue,
    onPrimary = White,
    background = White,
    onBackground = Black45,
    surface = Gray200,
    onSurface = Gray70,
)

@Composable
fun BlaBlaCarTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    
    CompositionLocalProvider(LocalDimensions provides Dimensions()) {
        
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}