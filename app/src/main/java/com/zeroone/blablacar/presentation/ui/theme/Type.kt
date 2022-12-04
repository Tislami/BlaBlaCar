package com.zeroone.blablacar.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.zeroone.blablacar.R


val FontFamily.Companion.HarmoniaSansBold
    get() = FontFamily(
        Font(R.font.harmonia_sans_w01_bold),
        )

val FontFamily.Companion.HarmoniaSansRegular
    get() = FontFamily(
        Font(R.font.harmonia_sans_w01_regular),
        )


val Typography = Typography(

    h1 = TextStyle(
        fontFamily = FontFamily.HarmoniaSansBold,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.HarmoniaSansBold,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
    ),
    body1 = TextStyle(
        fontFamily = FontFamily.HarmoniaSansBold,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    button = TextStyle(
        fontFamily = FontFamily.HarmoniaSansBold,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = FontFamily.HarmoniaSansBold,
        fontWeight = FontWeight.Thin,
        fontSize = 18.sp
    ),

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)