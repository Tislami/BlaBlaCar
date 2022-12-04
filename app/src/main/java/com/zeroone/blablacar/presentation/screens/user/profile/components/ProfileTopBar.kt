package com.zeroone.blablacar.presentation.screens.user.profile.components


import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zeroone.blablacar.R
import com.zeroone.blablacar.domain.model.User

@Composable
fun ProfileTopBar(
    backOnClick: () -> Unit,
    settingOnClick: () -> Unit
) {
    var selectedIndex by remember { mutableStateOf(0) }
    TabRow(
        selectedTabIndex = selectedIndex,
        backgroundColor = Color.Transparent,
        divider = {
            TabRowDefaults.Divider(color = Color.Transparent)
        }
    ) {
        Tab(
            text = { Text(stringResource(id = R.string.about)) },
            selected = selectedIndex == 0,
            onClick = {
                selectedIndex = 0
            },
            selectedContentColor = MaterialTheme.colors.onBackground,
            unselectedContentColor = MaterialTheme.colors.primary,
            )

        Tab(
            text = { Text(stringResource(id = R.string.profile)) },
            selected = selectedIndex == 1,
            onClick = {
                selectedIndex = 1
            },
            selectedContentColor = MaterialTheme.colors.onBackground,
            unselectedContentColor = MaterialTheme.colors.primary,
        )
    }
}