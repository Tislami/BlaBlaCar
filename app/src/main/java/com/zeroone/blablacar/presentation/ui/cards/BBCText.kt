package com.zeroone.blablacar.presentation.ui.cards

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
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
