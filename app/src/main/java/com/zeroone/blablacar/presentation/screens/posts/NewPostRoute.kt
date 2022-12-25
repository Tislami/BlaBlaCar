package com.zeroone.blablacar.presentation.screens.posts

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.zeroone.blablacar.R
import com.zeroone.blablacar.presentation.screens.main.Routes
import com.zeroone.blablacar.presentation.ui.components.BBCTextField

@Composable
fun NewPostRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    NewPostScreen(
        modifier = modifier,
        closeOnClick = { navController.navigate(Routes.Home.route) }
    )
}

@Composable
private fun NewPostScreen(
    modifier: Modifier = Modifier,
    closeOnClick: () -> Unit,
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            NewPostTopAppBar(
                buttonIcon = Icons.Default.Close,
                onClick = closeOnClick,
            )
        },
        content = {
            NewPostContent(
                modifier = modifier.padding(it),
                fromOnclick = {},
                toOnclick = {},
            )
        },
    )
}


@Composable
private fun NewPostContent(
    modifier: Modifier = Modifier,
    fromOnclick: () -> Unit,
    toOnclick: () -> Unit,
) {

    val contentVisibleState = MutableTransitionState(initialState = false)
        .apply { targetState = true }

    val toFieldVisibleState = MutableTransitionState(initialState = false)


    val enterTransition = slideInVertically(
        animationSpec = tween(1000),
        initialOffsetY = { 2000 }
    )

    AnimatedVisibility(
        visibleState = contentVisibleState,
        enter = enterTransition,
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
        ) {

            NewPostTextField(
                value = "",
                onValueChange = { },
                labelText = stringResource(id = R.string.from),
                doneOnclick = {toFieldVisibleState.targetState=true}
            )

            AnimatedVisibility(
                visibleState = toFieldVisibleState,
                enter = enterTransition,
                modifier = Modifier.zIndex(1f)
            ) {
                NewPostTextField(
                    value = "",
                    onValueChange = { },
                    labelText = stringResource(id = R.string.to),
                    doneOnclick = {}
                )
            }

            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 16.dp),
                shape = MaterialTheme.shapes.medium,
                color = Color.Green
            ) {
                Image(imageVector = Icons.Default.LocationOn, contentDescription = null)
            }

        }
    }
}

@Composable
private fun NewPostTextField(
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    doneOnclick: () -> Unit,
) {
    val visibleState = MutableTransitionState(initialState = false)
    Row(verticalAlignment = Alignment.CenterVertically) {
        BBCTextField(
            modifier = Modifier
                .weight(1f)
                .onFocusChanged { visibleState.targetState = it.isFocused },
            value = value,
            onValueChange = onValueChange,
            labelText = labelText
        )
        AnimatedVisibility(
            visibleState = visibleState,
            enter = fadeIn(tween(1000)),
        ) {
            IconButton(onClick = doneOnclick) {
                Icon(
                    modifier = Modifier.size(25.dp),
                    imageVector = Icons.Default.Done,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary
                )
            }
        }
    }
}