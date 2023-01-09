package com.zeroone.blablacar.presentation.ui.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import com.zeroone.blablacar.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.zeroone.blablacar.domain.model.Post2
import com.zeroone.blablacar.domain.model.User
import com.zeroone.blablacar.domain.model.defaultUser
import com.zeroone.blablacar.presentation.ui.theme.LocalDimensions

@Composable
fun PostCard(
    post: Post2,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        backgroundColor = MaterialTheme.colors.background,
        shape = MaterialTheme.shapes.medium,
        elevation = 8.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Title("${post.date} , ${post.time}", post.price)
            Spacer(modifier = Modifier.height(16.dp))
            LocationField(post.fromAddress)
            LocationField(post.toAddress)
            Spacer(modifier = Modifier.height(16.dp))
            UserField(defaultUser)
            CustomerCount(post)
        }
    }
}

@Composable
private fun Title(dateTime: String, price: Float) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = dateTime,
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.weight(1f)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {

            Icon(
                painter = painterResource(id = R.drawable.manat),
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = price.toString(),
                fontSize = 18.sp,
            )
        }
    }
}

@Composable
private fun LocationField(locationName: String) {
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Icon(
            painter = painterResource(id = R.drawable.location_icon_24),
            contentDescription = null,
            tint = MaterialTheme.colors.onBackground
        )

        Text(
            text = locationName,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onBackground
        )
    }
}

@Composable
private fun UserField(user: User) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Surface(
            shape = CircleShape,
            modifier = Modifier
                .size(LocalDimensions.current.profileImageSizeMin)
                .border(BorderStroke(1.dp, MaterialTheme.colors.onBackground), CircleShape)
        ) {

            AsyncImage(
                model = user.photoUrl,
                contentDescription = stringResource(id = R.string.profile_photo),
                contentScale = ContentScale.Crop,
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = "${user.name}.${user.surname}",
                color = MaterialTheme.colors.onBackground
            )
            Text(
                text = user.rate,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun CustomerCount(post: Post2) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom
    ) {
        if (post.personCount > 4) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = post.personCount.toString(),
                fontSize = 16.sp
            )
        } else {
            repeat(post.personCount) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}