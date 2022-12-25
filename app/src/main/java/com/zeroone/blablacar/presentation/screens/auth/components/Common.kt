package com.zeroone.blablacar.presentation.screens.auth.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zeroone.blablacar.R


@Composable
fun AuthLogo(@StringRes resourceId: Int) {
    Text(text = stringResource(id = resourceId))
}
