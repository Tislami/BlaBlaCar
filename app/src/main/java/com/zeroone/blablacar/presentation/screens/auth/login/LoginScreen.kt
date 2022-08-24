package com.zeroone.blablacar.presentation.screens.auth.login

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zeroone.blablacar.R
import com.zeroone.blablacar.presentation.screens.auth.AuthLogo
import com.zeroone.blablacar.presentation.screens.auth.AuthWithSocialMedia
import com.zeroone.blablacar.presentation.screens.auth.HaveAccount
import com.zeroone.blablacar.presentation.ui.cards.BBCButton
import com.zeroone.blablacar.presentation.ui.cards.BBCTextField
import com.zeroone.blablacar.utils.TAG

@Composable
fun LoginScreen(
    navigateToSignUp: () -> Unit,
    navigateToHome: () -> Unit,
    googleOnClick: () -> Unit,
    fbOnClick: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {

    Log.d("Screen", "LoginScreen: ")

    Content(modifier, navigateToHome, googleOnClick, fbOnClick, navigateToSignUp)
}

@Composable
private fun Content(
    modifier: Modifier,
    navigateToHome: () -> Unit,
    googleOnClick: () -> Unit,
    fbOnClick: () -> Unit,
    navigateToSignUp: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        AuthLogo(resourceId = R.string.sing_in) // TODO: Change logo

        Spacer(modifier = Modifier.height(16.dp))

        SingIn(navigateToHome = navigateToHome)

        AuthWithSocialMedia(
            googleOnClick = googleOnClick,
            fbOnClick = fbOnClick
        )

        HaveAccount(
            mainTextResourceId = R.string.dont_have_an_account,
            buttonTextResourceId = R.string.sing_up,
            onClick = navigateToSignUp
        )
    }
}


@Composable
private fun SingIn(navigateToHome: () -> Unit) {
    BBCTextField(
        text = "",
        onValueChange = {},
        hint = stringResource(id = R.string.e_mail),
        leadingIcon = Icons.Default.Email,
        trailingIcon = Icons.Default.Clear,
    )

    BBCTextField(
        text = "",
        onValueChange = {},
        hint = stringResource(id = R.string.password),
        leadingIcon = Icons.Default.Lock,
        trailingIcon = Icons.Default.Close,
    )


    BBCButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        text = stringResource(id = R.string.sing_in),
        onClick = { navigateToHome() },
    )
}





