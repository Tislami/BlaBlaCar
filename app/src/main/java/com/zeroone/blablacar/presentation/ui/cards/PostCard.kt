package com.zeroone.blablacar.presentation.ui.cards

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import com.zeroone.blablacar.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.zeroone.blablacar.domain.model.User
import com.zeroone.blablacar.presentation.ui.theme.LocalDimensions

@Composable
fun PostCard(
    dateTime: String,
    fromLocation: String,
    toLocation: String,
    user: User,
    onClick:()-> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = MaterialTheme.shapes.medium,
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 32.dp)
        ) {
            Title(dateTime)
            Spacer(modifier = Modifier.height(16.dp))
            LocationField(fromLocation)
            LocationField(toLocation)
            Spacer(modifier = Modifier.height(16.dp))
            UserField(user)
        }
    }
}

@Composable
private fun Title(dateTime: String) {
    Text(
        text = dateTime,
        style = MaterialTheme.typography.caption
    )
}

@Composable
private fun UserField(user: User) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Surface(
            shape = CircleShape,
            modifier = Modifier
                .size(LocalDimensions.current.profileImageSize)
        ) {

            AsyncImage(
                model = user.photoUrl,
                contentDescription = stringResource(id = R.string.profile_photo),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(text = user.nickname)
    }
}

@Composable
private fun LocationField(locationName: String) {
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Icon(
            painter = painterResource(id = R.drawable.location_icon),
            contentDescription = null
        )

        Text(
            text = locationName,
            style = MaterialTheme.typography.body1
        )
    }
}