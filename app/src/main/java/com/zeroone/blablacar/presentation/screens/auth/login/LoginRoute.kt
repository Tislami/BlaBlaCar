package com.zeroone.blablacar.presentation.screens.auth.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zeroone.blablacar.R
import com.zeroone.blablacar.presentation.screens.auth.AuthState
import com.zeroone.blablacar.presentation.screens.auth.AuthViewModel
import com.zeroone.blablacar.presentation.screens.auth.LoginState
import com.zeroone.blablacar.presentation.screens.auth.components.AuthTopAppBar
import com.zeroone.blablacar.presentation.ui.components.BBCTextField
import com.zeroone.blablacar.presentation.ui.Loading
import com.zeroone.blablacar.presentation.ui.components.BBCGreetingText
import com.zeroone.blablacar.presentation.ui.components.BBCPrimaryButton
import com.zeroone.blablacar.presentation.ui.components.BBCTextButton
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginRoute(
    modifier: Modifier = Modifier,
    backOnClick: () -> Unit,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val authState by authViewModel.authStates


    LaunchedEffect(key1 = Unit) {
        authViewModel.loginState.collectLatest { event ->
            when (event) {
                is LoginState.Error -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
                LoginState.Logged -> {
                    scaffoldState.snackbarHostState.showSnackbar("Logged")
                }
            }
        }
    }


    if (authViewModel.isLoading.value)
        Loading()
    else
        LoginScreen(
            modifier = modifier,
            scaffoldState = scaffoldState,
            authState = authState,
            eMailValueOnChange = authViewModel::setEmail,
            passwordValueOnChange = authViewModel::setPassword,
            backOnClick = backOnClick,
            fabButtonOnClick = authViewModel::login,
        )
}


@Composable
private fun LoginScreen(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState,
    authState: AuthState,
    eMailValueOnChange: (String) -> Unit,
    passwordValueOnChange: (String) -> Unit,
    backOnClick: () -> Unit,
    fabButtonOnClick: (String, String) -> Unit,
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AuthTopAppBar(
                buttonIcon = Icons.Default.ArrowBack,
                onClick = backOnClick
            )
        },
        content = { innerPadding ->
            LoginContent(
                eMail = authState.eMail,
                password = authState.password,
                passwordValueOnChange = passwordValueOnChange,
                eMailValueOnChange = eMailValueOnChange,
                modifier = modifier.padding(innerPadding),
            )
        },
        floatingActionButton = {
            BBCPrimaryButton(
                text = stringResource(id = R.string.login),
                onClick = {
                    fabButtonOnClick(authState.eMail, authState.password)
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    )
}

@Composable
private fun LoginContent(
    modifier: Modifier = Modifier,
    eMail: String,
    password: String,
    eMailValueOnChange: (String) -> Unit,
    passwordValueOnChange: (String) -> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {
        BBCGreetingText(stringResource(id = R.string.what_is_email_and_password))

        BBCTextField(eMail, eMailValueOnChange, stringResource(id = R.string.e_mail))

        Spacer(modifier = Modifier.height(8.dp))

        BBCTextField(password, passwordValueOnChange, stringResource(id = R.string.password))

        BBCTextButton(text = stringResource(id = R.string.forgot_password)) {

        }
    }
}