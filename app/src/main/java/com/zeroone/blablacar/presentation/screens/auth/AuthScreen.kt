package com.zeroone.blablacar.presentation.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zeroone.blablacar.R
import com.zeroone.blablacar.presentation.ui.cards.BBCButton

@Composable
fun AuthScreen() {

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

        Text(text = "Welcome to the ... ")

        Spacer(modifier = Modifier.height(16.dp))


        BBCButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            text = stringResource(id = R.string.sing_in),
            onClick = { /*TODO*/ },
        )

        BBCButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            text = stringResource(id = R.string.sing_up),
            onClick = { /*TODO*/ },
        )

    }
}



