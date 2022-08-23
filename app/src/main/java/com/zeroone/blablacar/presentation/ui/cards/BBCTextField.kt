package com.zeroone.blablacar.presentation.ui.cards

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


@Composable
fun BBCTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    hint: String,
    trailingIcon: ImageVector? = null,
    leadingIcon: ImageVector? = null,
    trailingOnClick: () -> Unit = { },
    isPasswordVisible: Boolean = false,
) {
    TextField(
        value = text,
        onValueChange = { onValueChange(it) },
        label = {
            BBCText(text = hint)
        },
        leadingIcon = {
            if (leadingIcon != null) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null
                )
            }
        },
        trailingIcon = {
            IconButton(onClick = trailingOnClick) {
                if (trailingIcon != null) {
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = null
                    )
                }
            }
        },
        shape= MaterialTheme.shapes.small,
        modifier = modifier
            .fillMaxWidth()
            .paddingFromBaseline(bottom = 32.dp),
    )
}