package com.zeroone.blablacar.presentation.screens.user.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.zeroone.blablacar.R
import com.zeroone.blablacar.domain.model.User
import com.zeroone.blablacar.domain.model.defaultUser
import com.zeroone.blablacar.presentation.screens.user.profile.components.AboutContent
import com.zeroone.blablacar.presentation.screens.user.profile.components.AccountContent
import com.zeroone.blablacar.presentation.screens.user.profile.components.ProfileTopBar
import com.zeroone.blablacar.presentation.ui.theme.LocalDimensions


@Composable
fun ProfileRoute(
    modifier: Modifier = Modifier
) {
    ProfileScreen(
        modifier = modifier
    )
}

@Composable
private fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    val selectedIndex = remember { mutableStateOf(0) }
    Scaffold(
        topBar = {
            ProfileTopBar(selectedIndex)
        },
        content = { innerPadding ->
            if (selectedIndex.value == 0)
                AboutContent(modifier.padding(innerPadding))
            else
                AccountContent(modifier.padding(innerPadding))

        },
    )
}
