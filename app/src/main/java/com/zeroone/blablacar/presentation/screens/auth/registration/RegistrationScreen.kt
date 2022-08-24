package com.zeroone.blablacar.presentation.screens.auth.registration

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zeroone.blablacar.R
import com.zeroone.blablacar.presentation.screens.auth.AuthLogo
import com.zeroone.blablacar.presentation.screens.auth.AuthWithSocialMedia
import com.zeroone.blablacar.presentation.screens.auth.HaveAccount
import com.zeroone.blablacar.presentation.ui.cards.BBCButton
import com.zeroone.blablacar.presentation.ui.cards.BBCTextField
import com.zeroone.blablacar.utils.TAG

@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
    navigateToSignIn: () -> Unit,
    googleOnClick: () -> Unit,
    fbOnClick: () -> Unit,
) {
    Log.d("Screen", "RegistrationScreen: ")
    Content(modifier, navigateToHome, googleOnClick, fbOnClick, navigateToSignIn)
}

@Composable
private fun Content(
    modifier: Modifier,
    navigateToHome: () -> Unit,
    googleOnClick: () -> Unit,
    fbOnClick: () -> Unit,
    navigateToSignIn: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        AuthLogo(R.string.sing_up) // TODO: Change logo

        Spacer(modifier = Modifier.height(16.dp))

        SingUp(navigateToHome = navigateToHome)

        AuthWithSocialMedia(
            googleOnClick = googleOnClick,
            fbOnClick = fbOnClick
        )

        HaveAccount(
            mainTextResourceId = R.string.do_you_have_an_account,
            buttonTextResourceId = R.string.sing_in,
            onClick = navigateToSignIn
        )
    }
}


@Composable
private fun SingUp(navigateToHome: () -> Unit) {
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
        hint = stringResource(id = R.string.name),
        leadingIcon = Icons.Default.Person,
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
        text = stringResource(id = R.string.sing_up),
        onClick = { navigateToHome() },
    )
}




