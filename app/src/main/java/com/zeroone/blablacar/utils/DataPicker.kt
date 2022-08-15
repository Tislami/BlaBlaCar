package com.zeroone.blablacar.utils

import android.app.DatePickerDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext


@Composable
fun rememberDatePicker(): DatePickerDialog {
    val context = LocalContext.current
    val datePickerDialog = DatePickerDialog(context)
    return remember { datePickerDialog }
}