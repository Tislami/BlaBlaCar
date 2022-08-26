package com.zeroone.blablacar.utils

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.TimePicker
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext


@Composable
fun rememberDatePicker(): DatePickerDialog {
    val context = LocalContext.current
    val datePickerDialog = DatePickerDialog(context)
    return remember { datePickerDialog }
}

@Composable
fun rememberTimePicker(): TimePickerDialog {

    val mHour = 0
    val mMinute = 0

    val context = LocalContext.current
    val timePickerDialog = TimePickerDialog(
        context,
        TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute -> },
        mHour, mMinute, false
    )

    return remember { timePickerDialog }
}