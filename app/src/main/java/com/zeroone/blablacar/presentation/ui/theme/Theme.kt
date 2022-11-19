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
    onPrimary = Color.White,
    primaryVariant = Purple700,
    secondary = Teal200,
    onSecondary = Color.White,
    background = Color.White,
    onSurface = Color.Black,
    )

private val LightColorPalette = lightColors(
    primary = Blue,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Gray,
    onBackground = Color.Black,
    onSurface = Color.Black,
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