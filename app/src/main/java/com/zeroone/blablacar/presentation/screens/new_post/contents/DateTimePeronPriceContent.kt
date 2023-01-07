package com.zeroone.blablacar.presentation.screens.new_post.contents

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.zeroone.blablacar.presentation.screens.new_post.NewPostState
import com.zeroone.blablacar.presentation.ui.Loading
import java.util.*

@Composable
fun DateTimePeronPriceContent(
    modifier: Modifier = Modifier,
    newPostState: NewPostState,
    setData : (String)->Unit,
    setTime : (String)->Unit,
    setPersonCount : (Int)->Unit,
    setPrice : (Int)->Unit,
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val year: Int = calendar.get(Calendar.YEAR)
    val month: Int = calendar.get(Calendar.MONTH)
    val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
    val hour: Int = calendar.get(Calendar.HOUR)
    val minute: Int = calendar.get(Calendar.MINUTE)

    calendar.time = Date()

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, _year: Int, _month: Int, _dayOfMonth: Int ->
            setData( "$_dayOfMonth/${_month + 1}/$_year")
        }, year, month, day
    )

    val timePickerDialog = TimePickerDialog(
        context,
        { _: TimePicker, _hourOfDay: Int, _minute: Int ->
            setTime("$_hourOfDay : $_minute")
        }, hour, minute, false
    )

    Column(modifier = modifier.padding(vertical = 16.dp)) {
        Card {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { datePickerDialog.show() }
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Date")
                Text(text = newPostState.date)
            }
        }

        Card {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { timePickerDialog.show() }
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Time")
                Text(text = newPostState.time)
            }
        }
        Card {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Person")
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { setPersonCount(1)}) {
                        Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = null)
                    }
                    Text(text = newPostState.personCount.toString())

                    IconButton(onClick = {setPersonCount(-1)}) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null
                        )
                    }
                }
            }
        }

        Card {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Price")
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { setPrice(+1) }) {
                        Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = null)
                    }
                    Text(text = newPostState.price.toString())

                    IconButton(onClick = { setPrice(-1)}) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Card(content: @Composable () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colors.background,
        elevation = 8.dp,
        content = content
    )
}

