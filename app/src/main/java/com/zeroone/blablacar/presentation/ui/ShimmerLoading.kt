package com.zeroone.blablacar.presentation.ui

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zeroone.blablacar.presentation.ui.theme.LocalDimensions

@Composable
fun CardShimmerLoading(isLoading: Boolean) {

    if (isLoading) {
        Card(
            modifier = Modifier
                .fillMaxWidth() ,
            shape = MaterialTheme.shapes.medium,
        ) {
            Column(modifier = Modifier.shimmerEffect()) {
                Text(text = "", modifier = Modifier.shimmerEffect())
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "", modifier = Modifier.shimmerEffect())
                Text(text = "", modifier = Modifier.shimmerEffect())
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Surface(
                        shape = CircleShape,
                        modifier = Modifier
                            .size(LocalDimensions.current.profileImageSizeMin)
                            .border(
                                BorderStroke(1.dp, MaterialTheme.colors.onBackground),
                                CircleShape
                            )
                    ) {
                    }
                    Text(text = "", modifier = Modifier.shimmerEffect())
                }
            }
        }
    }
}

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember { mutableStateOf(IntSize.Zero) }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue =  -size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000),
        )
    )
    background(
        Brush.linearGradient(
            colors = listOf(
                Color(125, 125, 125, 255),
                Color(255, 255, 255, 255),
                Color(165, 165, 165, 255),
            ),
            start = Offset(startOffsetX, 0.0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    ).onGloballyPositioned {
        size = it.size
    }
}
