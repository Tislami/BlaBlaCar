package com.zeroone.blablacar.presentation.screens.post.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zeroone.blablacar.R
import com.zeroone.blablacar.domain.model.Post
import com.zeroone.blablacar.presentation.ui.cards.BBCImage
import com.zeroone.blablacar.presentation.ui.cards.BBCPostText
import com.zeroone.blablacar.presentation.ui.cards.BBCText

@Composable
private fun Location(post: Post) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Destination(stringResource(id = R.string.from), post.from)
        BBCImage(imageVector = Icons.Default.ArrowForward)
        Destination(stringResource(id = R.string.to), post.to)
    }
}

@Composable
private fun Destination(title: String, location: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        BBCText(text = title)
        BBCPostText(text = location)
    }
}

@Composable
private fun DateAndTime(post: Post) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            BBCText(text = stringResource(id = R.string.date))
            Spacer(modifier = Modifier.height(8.dp))
            BBCText(
                text = post.date,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            BBCText(text = post.dayOfWeek())
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            BBCText(text = stringResource(id = R.string.time))
            Spacer(modifier = Modifier.height(8.dp))
            BBCText(
                text = post.time,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun LocationAndDate(post: Post) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        BBCText(
            text = stringResource(id = R.string.location_and_date),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        Location(post)

        Divider(
            thickness = .5.dp,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        DateAndTime(post)

        Spacer(modifier = Modifier.height(16.dp))
    }
}