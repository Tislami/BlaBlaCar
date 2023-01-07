package com.zeroone.blablacar.presentation.screens.expanded_post

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.zeroone.blablacar.R

@Composable
fun ExpandedPostRoute(
    modifier: Modifier = Modifier,
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 32.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = 8.dp,
    ) {
        Column(modifier = Modifier.padding(8.dp)) {

            Title("25/01/2023 , 15:00")

            LocationField(locationName = "Şeki Avtvağzal")
            LocationField(locationName = "Bakı Avtvağzal")
        }
    }
}

@Composable
private fun Title(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.caption,
        color = MaterialTheme.colors.primary
    )
}

@Composable
private fun LocationField(locationName: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = R.drawable.location_icon_24),
            contentDescription = null,
            tint = MaterialTheme.colors.onBackground,
        )

        Text(
            text = locationName,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onBackground
        )
    }
}
