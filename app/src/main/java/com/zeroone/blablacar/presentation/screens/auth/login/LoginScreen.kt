package com.zeroone.blablacar.presentation.screens.auth.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zeroone.blablacar.R
import com.zeroone.blablacar.ui.cards.BBCButton
import com.zeroone.blablacar.ui.cards.BBCText
import com.zeroone.blablacar.ui.cards.BBCTextButton
import com.zeroone.blablacar.ui.cards.BBCTextField

@Composable
fun LoginScreen() {

    Scaffold(
        content = { Content() },
    )
}

@Composable
private fun Content() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 128.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(text = stringResource(id = R.string.sing_in))

        Spacer(modifier = Modifier.height(16.dp))


        //______Text Fields ______
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
            onClick = { /*TODO*/ },
        )


        //______OR______
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.paddingFromBaseline(top = 32.dp)
        ) {
            Divider(modifier = Modifier.weight(1f))
            BBCText(text = stringResource(id = R.string.or))
            Divider(modifier = Modifier.weight(1f))
        }

        BBCButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            text = stringResource(id = R.string.sing_in_with_google),
            onClick = { /*TODO*/ },
        )

        BBCButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            text = stringResource(id = R.string.sing_in_with_facebook),
            onClick = { /*TODO*/ },
        )


        //______Have Account ______

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BBCText(text = stringResource(id = R.string.dont_have_an_account))
            BBCTextButton(text = stringResource(id = R.string.sing_up)){}
        }
    }
}



