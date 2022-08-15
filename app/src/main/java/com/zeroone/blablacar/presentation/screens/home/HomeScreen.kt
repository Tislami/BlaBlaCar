package com.zeroone.blablacar.presentation.screens.home

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.zeroone.blablacar.domain.model.defaultPost
import com.zeroone.blablacar.ui.cards.BBCButton
import com.zeroone.blablacar.ui.cards.BBCTextField
import com.zeroone.blablacar.presentation.screens.post.components.Post

@Composable
fun HomeScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Search()

        LazyColumn(content = {
            items(10){
                Post(post = defaultPost){}
            }
        })
    }
}


@Composable
fun Search() {

    Surface(
        shape = MaterialTheme.shapes.medium,
        color = Color(33, 150, 243, 255),
        elevation = 8.dp
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)) {

            Spacer(modifier = Modifier.height(32.dp))

            Location()

            DateAndPersonRange()

            BBCButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                text = "Search",
                onClick = { /*TODO*/ },
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }


}

@Composable
private fun Location() {

    BBCTextField(text = "", onValueChange = {}, hint = "From")

    BBCTextField(text = "", onValueChange = {}, hint = "To")
}

@Composable
private fun DateAndPersonRange() {
    Row(modifier = Modifier.fillMaxWidth()) {

        //DateRange
        Surface(
            modifier = Modifier
                .height(80.dp)
                .weight(.90f),
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colors.primary
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Default.DateRange, contentDescription = "")
                }

                Text(text = "05")
                Text(text = "April")
                Text(text = "2022")
            }
        }
        
        Spacer(modifier = Modifier.width(4.dp))

        //PersonRange
        Surface(
            modifier = Modifier
                .height(80.dp)
                .weight(.15f),
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colors.primary
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Text(text = "99")
            }
        }
    }
}


@Composable
fun rememberDatePicker(): DatePickerDialog {
    val context = LocalContext.current
    val datePickerDialog = DatePickerDialog(context)
    return remember { datePickerDialog }
}
