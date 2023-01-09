package com.zeroone.blablacar.presentation.screens.new_post.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zeroone.blablacar.presentation.screens.new_post.NewPostState


@Composable
fun SetContent(
    modifier: Modifier = Modifier,
    title:String,
    value: String,
    image: Painter?=null,
    onSetClick: (Int) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
    ) {
        Text(text = title)

        Surface(
            elevation = 8.dp,
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colors.background,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {

                ButtonField("-", onClick = { onSetClick(-1) })
                TextField(value, image)
                ButtonField("+", onClick = { onSetClick(1) })
            }
        }
    }
}


@Composable
private fun TextField(
    value: String,
    image: Painter?,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = value,
            color = MaterialTheme.colors.onBackground,
            fontSize = 52.sp,
        )
        Spacer(modifier = Modifier.width(16.dp))

        if (image!=null) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
        }
    }
}

@Composable
private fun ButtonField(
    title: String,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(50.dp)
    ) {
        Surface(
            shape = CircleShape,
            border = BorderStroke(1.dp, MaterialTheme.colors.primary),
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = title,
                color = MaterialTheme.colors.primary,
                fontSize = 36.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}