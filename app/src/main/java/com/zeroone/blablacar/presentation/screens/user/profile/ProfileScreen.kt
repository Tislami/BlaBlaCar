package com.zeroone.blablacar.presentation.screens.user.profile

import android.util.Log
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.motion.widget.MotionScene
import com.zeroone.blablacar.R
import com.zeroone.blablacar.domain.model.User
import com.zeroone.blablacar.domain.model.defaultPost
import com.zeroone.blablacar.presentation.screens.home.components.Search
import com.zeroone.blablacar.presentation.ui.cards.BBCText
import com.zeroone.blablacar.utils.TAG

@Stable
@Composable
fun ProfileScreen(
    user: User = defaultPost.user,
    backOnClick: () -> Unit,
    settingOnClick: () -> Unit,
) {
    Log.d("Screen", "ProfileScreen: ")

    Scaffold(
        topBar = { TopBar(user, backOnClick, settingOnClick) },
        content = {



        },
        bottomBar = {}
    )
}

@Composable
private fun TopBar(
    user: User,
    backOnClick: () -> Unit,
    settingOnClick: () -> Unit
) {
    TopAppBar(
        title = { BBCText(text = "${user.name} ${user.surname}") },
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