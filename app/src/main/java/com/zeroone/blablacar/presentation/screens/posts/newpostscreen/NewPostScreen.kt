package com.zeroone.blablacar.presentation.screens.posts.newpostscreen/*
package com.zeroone.blablacar.presentation.screens.posts.newpostscreen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zeroone.blablacar.R
import com.zeroone.blablacar.presentation.ui.cards.BBCBoldText
import com.zeroone.blablacar.presentation.ui.cards.BBCCard
import com.zeroone.blablacar.presentation.ui.cards.BBCText
import com.zeroone.blablacar.presentation.ui.components.BBCTextField
import com.zeroone.blablacar.utils.MONTH
import com.zeroone.blablacar.utils.rememberDatePicker
import com.zeroone.blablacar.utils.rememberTimePicker
import java.util.*

@Composable
fun NewPostScreen(
    modifier: Modifier = Modifier,
    backOnClick: () -> Unit,
    shareClick: () -> Unit,
) {

    val onValueChangeFrom = remember { mutableStateOf("") }
    val onValueChangeTo = remember { mutableStateOf("") }
    val isDriver = remember { mutableStateOf(true) }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "New Post") },
                navigationIcon = {
                    IconButton(onClick = backOnClick) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        content = {
            Column(modifier = modifier) {

                Location(stringResource(id = R.string.from), onValueChangeFrom)
                Location(stringResource(id = R.string.to), onValueChangeTo)
                DateAndTime(modifier)
                DriverCustomer(isDriver)
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.width(100.dp),
                onClick = shareClick
            ) {
                BBCText(text = "Share")
            }
        }
    )
}


@Composable
private fun Location(
    title: String,
    onValueChange: MutableState<String>
) {
    Column() {
        BBCBoldText(text = title)
        BBCTextField(
            text = onValueChange.value,
            onValueChange = { onValueChange.value = it },
            leadingIcon = Icons.Default.LocationOn,
            hint = stringResource(id = R.string.from)
        )
    }
}

@Composable
private fun DateAndTime(modifier: Modifier) {

    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)
    val mHour = c[Calendar.HOUR_OF_DAY]
    val mMinute = c[Calendar.MINUTE]

    val date = remember { mutableStateOf("$day  ${MONTH[month]}") }
    val datePickerDialog = DatePickerDialog(
        context,
        { _, _ , month: Int, day: Int ->
            date.value = "$day  ${MONTH[month]}"
        },year,month,day
    )


    val time = remember { mutableStateOf("$mHour : $mMinute") }
    val timePickerDialog = TimePickerDialog(
        context,
        {_, hour : Int, minute: Int ->
            time.value = "$hour:$minute"
        }, mHour, mMinute, false
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier.clickable {
            datePickerDialog.show()
        }
        ) {
            BBCBoldText(text = stringResource(id = R.string.date))
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colors.primary)
                    .height(50.dp)
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = "")
                Spacer(modifier = Modifier.width(8.dp))
                BBCText(text = date.value)
            }
        }

        Column(modifier.clickable {
            timePickerDialog.show()
        }) {
            BBCBoldText(text = stringResource(id = R.string.time))
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colors.primary)
                    .height(50.dp)
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(painter = painterResource(id = R.drawable.clock_logo), contentDescription = "")
                Spacer(modifier = Modifier.width(8.dp))
                BBCText(text = time.value)
            }
        }
    }
}

@Composable
private fun DriverCustomer(isDriver: MutableState<Boolean>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column() {
            BBCBoldText(text = "Driver")
            RadioButton(selected = isDriver.value, onClick = { isDriver.value = true })
        }
        Column() {
            BBCBoldText(text = "Customer")
            RadioButton(
                selected = !isDriver.value,
                onClick = { isDriver.value = false })
        }
    }
}*/
