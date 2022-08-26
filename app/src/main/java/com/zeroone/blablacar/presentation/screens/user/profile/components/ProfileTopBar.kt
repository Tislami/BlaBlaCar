package com.zeroone.blablacar.presentation.screens.user.profile.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.zeroone.blablacar.R
import com.zeroone.blablacar.domain.model.User
import com.zeroone.blablacar.presentation.ui.cards.BBCText


@Composable
fun ProfileTopBar(
    user: User,
    backOnClick: () -> Unit,
    settingOnClick: () -> Unit
) {
    TopAppBar(
        title = { BBCText(text = stringResource(id = R.string.profile)) },
        navigationIcon = {
            IconButton(onClick = backOnClick) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = settingOnClick) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = null)
            }
        }
    )
}