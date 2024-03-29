package com.zeroone.blablacar.presentation.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.zeroone.blablacar.R
import com.zeroone.blablacar.presentation.ui.components.BBCTextButton
import com.zeroone.blablacar.presentation.ui.components.BBCGreetingText
import com.zeroone.blablacar.presentation.ui.components.BBCPrimaryButton
import com.zeroone.blablacar.presentation.ui.theme.LocalDimensions

@Composable
fun IntroScreen(
    modifier: Modifier = Modifier,
    loginOnClick : ()-> Unit,
    signUpOnClick : ()-> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .weight(.65f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.blablacar_logo),
                contentDescription = stringResource(id = R.string.app_name)
            )
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(.35f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            BBCGreetingText(
                text = stringResource(id = R.string.greeting),
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(LocalDimensions.current.extraLarge))

            BBCPrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.sing_up),
                onClick = signUpOnClick
            )


            BBCTextButton(
                text = stringResource(id = R.string.login),
                onClick = loginOnClick
            )
        }
    }
}







