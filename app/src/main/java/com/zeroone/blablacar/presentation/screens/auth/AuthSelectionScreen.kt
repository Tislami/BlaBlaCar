package com.zeroone.blablacar.presentation.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zeroone.blablacar.R
import com.zeroone.blablacar.presentation.screens.auth.login.AuthTopAppBar
import com.zeroone.blablacar.presentation.screens.auth.login.components.OtherChoiceText
import com.zeroone.blablacar.presentation.screens.auth.components.ContinueButton
import com.zeroone.blablacar.presentation.screens.auth.navigation.AuthScreens
import com.zeroone.blablacar.presentation.ui.components.BBCGreetingText
import com.zeroone.blablacar.presentation.ui.components.BBCTextButton

@Composable
fun AuthSelectionScreen(
    modifier: Modifier,
    selection: String,
    navController: NavController,
) {

    var title by remember { mutableStateOf("") }
    var otherChoiceText by remember { mutableStateOf("") }
    var otherChoiceButtonText by remember { mutableStateOf("") }
    var otherChoiceOnClick by remember { mutableStateOf({ }) }
    var fbOnClick by remember { mutableStateOf({ }) }
    var eMailOnClick by remember { mutableStateOf({ }) }

    when (selection) {
        "login" -> {
            title = stringResource(id = R.string.how_would_you_like_to_log_in)
            otherChoiceText = stringResource(id = R.string.dont_have_an_account)
            otherChoiceButtonText = stringResource(id = R.string.sing_up)
            otherChoiceOnClick =
                { navController.navigate(AuthScreens.AuthScreen.route + "/sign_up") }
            fbOnClick = {}
            eMailOnClick = { navController.navigate(AuthScreens.AuthScreen.route + "/login") }
        }
        "sign_up" -> {
            title = stringResource(id = R.string.how_would_you_like_to_sign_up)
            otherChoiceText = stringResource(id = R.string.do_you_have_an_account)
            otherChoiceButtonText = stringResource(id = R.string.login)
            otherChoiceOnClick = { navController.navigate(AuthScreens.AuthScreen.route + "/login") }
            fbOnClick = {}
            eMailOnClick = { navController.navigate(AuthScreens.AuthScreen.route + "/sign_up") }
        }
    }

    Scaffold(
        topBar = {
            AuthTopAppBar(
                buttonIcon = Icons.Default.Close,
                onClick = { navController.navigate("") }
            )
        },
        content = { innerPadding ->
            AuthSelectionContent(
                modifier = modifier.padding(innerPadding),
                title = title,
                otherChoiceText = otherChoiceText,
                otherChoiceButtonText = otherChoiceButtonText,
                otherChoiceOnClick = otherChoiceOnClick,
                fbOnClick = fbOnClick,
                eMailOnClick = eMailOnClick,
            )
        },
    )
}

@Composable
fun AuthSelectionContent(
    modifier: Modifier,
    title: String,
    otherChoiceText: String,
    otherChoiceButtonText: String,
    fbOnClick: () -> Unit = {},
    eMailOnClick: () -> Unit = {},
    otherChoiceOnClick: () -> Unit = {},
) {

    Column(modifier = modifier.fillMaxSize()) {

        BBCGreetingText(title)

        Spacer(Modifier.height(24.dp))

        Buttons(fbOnClick, eMailOnClick)

        Spacer(Modifier.height(16.dp))

        OtherChoiceText(otherChoiceText)

        BBCTextButton(
            text = otherChoiceButtonText,
            onClick = otherChoiceOnClick
        )
    }
}


@Composable
private fun Buttons(
    fbOnClick: () -> Unit = {},
    eMailOnClick: () -> Unit = {},
) {

    ContinueButton(
        buttonIcon = painterResource(id = R.drawable.fb_logo),
        buttonText = stringResource(id = R.string.continue_with_facebook),
        onclick = fbOnClick
    )

    Divider()

    ContinueButton(
        buttonIcon = painterResource(id = R.drawable.mail_logo),
        buttonText = stringResource(id = R.string.continue_with_email),
        onclick = eMailOnClick
    )
}