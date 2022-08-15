package com.zeroone.blablacar.ui.cards

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun BBCText(text: String,
            fontSize: TextUnit = TextUnit.Unspecified,
            fontWeight: FontWeight? = null,
) {
    Text(text = text,
        fontSize = fontSize,
        fontWeight = fontWeight,
        )
}

@Composable
fun BBCBoldText(text: String) {
    Text(text = text,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,

    )
}

@Composable
fun BBCPostText(
    text: String,
) {
    Text(
        text = text,
        maxLines = 2,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
    )
}
