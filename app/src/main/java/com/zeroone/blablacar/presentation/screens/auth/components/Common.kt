package com.zeroone.blablacar.presentation.screens.auth.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zeroone.blablacar.R
import com.zeroone.blablacar.presentation.ui.cards.BBCButton
import com.zeroone.blablacar.presentation.ui.cards.BBCText
import com.zeroone.blablacar.presentation.ui.cards.BBCTextButton


@Composable
fun AuthLogo(@StringRes resourceId: Int) {
    Text(text = stringResource(id = resourceId))
}

@Composable
fun HaveAccount(
    @StringRes mainTextResourceId: Int,
    @StringRes buttonTextResourceId: Int,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BBCText(text = stringResource(mainTextResourceId))
        BBCTextButton(text = stringResource(buttonTextResourceId), onClick = onClick)
    }
}

@Composable
fun AuthWithSocialMedia(
    googleOnClick: () -> Unit,
    fbOnClick: () -> Unit,
) {
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
        text = stringResource(id = R.string.sing_up_with_google),
        onClick = googleOnClick,
    )

    BBCButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        text = stringResource(id = R.string.sing_up_with_facebook),
        onClick = fbOnClick,
    )
}
