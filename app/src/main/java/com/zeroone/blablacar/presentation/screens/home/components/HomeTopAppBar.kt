package com.zeroone.blablacar.presentation.screens.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zeroone.blablacar.R
import com.zeroone.blablacar.presentation.ui.components.BBCGreetingText
import com.zeroone.blablacar.presentation.ui.theme.LocalDimensions

@Composable
internal fun HomeTopAppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalDimensions.current.screenHorizontalPadding)
        ,
    ) {
        BBCGreetingText("Yolculuk ara")
    }
}