package com.zeroone.blablacar.presentation.screens.user.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.zeroone.blablacar.R
import com.zeroone.blablacar.domain.model.User
import com.zeroone.blablacar.domain.model.defaultUser
import com.zeroone.blablacar.presentation.screens.user.profile.components.ProfileTopBar
import com.zeroone.blablacar.presentation.ui.theme.LocalDimensions


@Composable
fun ProfileRoute(
    modifier: Modifier = Modifier,
) {
    ProfileScreen(modifier = modifier)
}

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,

    ) {

    Scaffold(
        topBar = {
            ProfileTopBar(backOnClick = { /*TODO*/ }) {

            }
        },
        content = { innerPadding ->
            ProfileContent(modifier.padding(innerPadding))
        },
    )
}


@Composable
private fun ProfileContent(
    modifier: Modifier = Modifier,
    user: User = defaultUser
) {
    Column(modifier = modifier.fillMaxSize()) {
        Head(user)
        Divider(modifier = Modifier.padding(vertical = 16.dp))
        About()
        Divider(modifier = Modifier.padding(vertical = 16.dp))
        Cars()
    }
}


@Composable
private fun Head(user: User) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = user.name,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                text = user.rate,
                style = MaterialTheme.typography.body1,
                fontSize = 14.sp,
                color = MaterialTheme.colors.onBackground.copy(alpha = .5f),
                modifier = Modifier.padding(top = 16.dp)
            )
        }

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

            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onBackground.copy(alpha = .5f),
                )
            }
        }
    }

    TextButton(
        onClick = { },
        contentPadding = PaddingValues(0.dp),

        ) {
        Text(
            text = stringResource(id = R.string.edit_profile),
            style = MaterialTheme.typography.body2
        )
    }
}


@Composable
private fun About() {
    Text(
        text = stringResource(id = R.string.about),
        style = MaterialTheme.typography.caption,
        modifier = Modifier.padding(top = 8.dp)
    )

    Text(
        text = stringResource(id = R.string.lorem),
        style = MaterialTheme.typography.body1,
        fontSize = 14.sp,
        color = MaterialTheme.colors.onBackground.copy(alpha = .5f),
        modifier = Modifier.padding(top = 16.dp)
    )
}

@Composable
private fun Cars() {
    Text(
        text = stringResource(id = R.string.cars),
        style = MaterialTheme.typography.caption,
        modifier = Modifier.padding(top = 8.dp)
    )

    TextButton(
        onClick = { },
        contentPadding = PaddingValues(0.dp),
    ) {

        Icon(imageVector = Icons.Rounded.AddCircle, contentDescription = null)

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = stringResource(id = R.string.add_car),
            style = MaterialTheme.typography.body2
        )
    }
}