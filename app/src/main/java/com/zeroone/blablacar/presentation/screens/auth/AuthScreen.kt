package com.zeroone.blablacar.presentation.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zeroone.blablacar.R
import com.zeroone.blablacar.presentation.screens.auth.login.AuthTopAppBar
import com.zeroone.blablacar.presentation.screens.auth.login.components.LoginTextField
import com.zeroone.blablacar.presentation.ui.components.BBCGreetingText
import com.zeroone.blablacar.presentation.ui.components.BBCPrimaryButton
import com.zeroone.blablacar.presentation.ui.components.BBCTextButton


@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    selection: String
) {

    var fabButtonText by remember { mutableStateOf("") }
    var fabButtonOnClick by remember { mutableStateOf({ }) }
    var forgotPasswordIsVisible by remember { mutableStateOf(false) }

    when (selection) {
        "login" -> {
            fabButtonText = stringResource(id = R.string.login)
            fabButtonOnClick = {}
            forgotPasswordIsVisible = true
        }
        "sign_up" -> {
            fabButtonText = stringResource(id = R.string.sing_up)
            fabButtonOnClick = {  }
            forgotPasswordIsVisible = false
        }
    }

    Scaffold(
        topBar = {
            AuthTopAppBar(
                buttonIcon = Icons.Default.ArrowBack,
                onClick = {
                    navController.popBackStack()
                }
            )
        },
        content = { innerPadding ->
            AuthContent(
                modifier=modifier.padding(innerPadding),
                forgotPasswordIsVisible = forgotPasswordIsVisible
            )
        },
        floatingActionButton = {
            BBCPrimaryButton(
                text = fabButtonText,
                onClick = fabButtonOnClick
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    )
}

@Composable
private fun AuthContent(
    modifier: Modifier = Modifier,
    forgotPasswordIsVisible: Boolean
) {
    Column(modifier = modifier.fillMaxSize()) {
        BBCGreetingText(stringResource(id = R.string.what_is_email_and_password))
        TextFields()

        if (forgotPasswordIsVisible)
        BBCTextButton(text = stringResource(id = R.string.forgot_password)) {

        }
    }
}

@Composable
private fun TextFields() {
    LoginTextField("", { }, stringResource(id = R.string.e_mail))

    Spacer(modifier = Modifier.height(8.dp))

    LoginTextField("", { }, stringResource(id = R.string.password))
}