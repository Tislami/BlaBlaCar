package com.zeroone.blablacar.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.zeroone.blablacar.R


val FontFamily.Companion.HarmoniaSans
    get() = FontFamily(
        Font(R.font.harmonia_sans_w01_bold),
        Font(R.font.harmonia_sans_w01_regular),
    )


val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.HarmoniaSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        fontFamily = FontFamily.HarmoniaSans,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    caption= TextStyle(
        fontFamily = FontFamily.HarmoniaSans,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    button = TextStyle(
        fontFamily = FontFamily.HarmoniaSans,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = FontFamily.HarmoniaSans,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
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