package com.zeroone.blablacar.presentation.ui.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun BBCImage(painter: Painter) {

    Image(painter = painter, contentDescription = null)
}

@Composable
fun BBCImage(imageVector: ImageVector) {

    Image(imageVector = imageVector, contentDescription = null)
}

@Composable
fun BBCCardImage(
    painter: Painter,
    height : Dp = 50.dp,
    width: Dp = 50.dp,
    shape: Shape = MaterialTheme.shapes.large,
) {
    Surface(
        modifier = Modifier.size(width,height),
        shape = shape,
        color = Color.Transparent,
    ) {
        Image(painter = painter, contentDescription = null)
    }
}

@Composable
fun BBCUserImage(
    painter: Painter,
    height : Dp = 50.dp,
    width: Dp = 50.dp,
) {
    Surface(
        modifier = Modifier.size(width,height),
        shape = CircleShape,
        color = Color.Transparent,
        border = BorderStroke(1.dp,MaterialTheme.colors.primary)
    ) {
        Image(painter = painter, contentDescription = null)
    }
}

@Composable
fun BBCUserImage(
    imageVector: ImageVector,
    height : Dp = 50.dp,
    width: Dp = 50.dp,
) {
    Surface(
        modifier = Modifier.size(width,height),
        shape = MaterialTheme.shapes.small,
        color = Color.Transparent,
        border = BorderStroke(1.dp,MaterialTheme.colors.primary)
    ) {
        Image(imageVector = imageVector, contentDescription = null)
    }
}
