package com.zeroone.blablacar.presentation.screens.expanded_post

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.widget.ConstraintSet.Transform
import coil.compose.AsyncImage
import com.zeroone.blablacar.R
import com.zeroone.blablacar.domain.model.Post
import com.zeroone.blablacar.domain.model.Post2
import com.zeroone.blablacar.domain.model.User
import com.zeroone.blablacar.domain.model.defaultUser
import com.zeroone.blablacar.presentation.ui.components.BBCTextButton
import com.zeroone.blablacar.presentation.ui.theme.LocalDimensions

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ExpandedPost(
    modifier: Modifier = Modifier,
    post: Post2,
    visibleState : MutableTransitionState<Boolean>,
    onclick: ()-> Unit,
) {
    val enterTransition = scaleIn(
        animationSpec = tween(1000),
        initialScale = 0.1f,
        transformOrigin = TransformOrigin.Center
        )

    val exitTransition = scaleOut(
        animationSpec = tween(1000),
        targetScale = 0.1f,
        transformOrigin = TransformOrigin.Center
    )

    AnimatedVisibility(
        visibleState = visibleState,
        enter = enterTransition,
        exit = exitTransition,
    ) {
        Surface(
            color = MaterialTheme.colors.background,
            modifier = modifier
                .fillMaxSize()
                .padding(vertical = 32.dp)
                .clickable { onclick() },
            shape = MaterialTheme.shapes.medium,
            elevation = 8.dp,
        ) {
            Column(modifier = Modifier.padding(8.dp)) {

                Title("${post.date}, ${post.time}")

                LocationField(locationName = post.fromAddress)
                LocationField(locationName = post.toAddress)

                PriceField(post.price.toString())
                UserField(user = defaultUser)

            }
        }
    }
}

@Composable
private fun PriceField(
    price: String,
) {
    Spacer(modifier = Modifier.height(16.dp))
    Divider(
        Modifier
            .background(Color.Gray.copy(alpha = .5f))
            .height(4.dp)
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Tek kişi için fiyat")
        Row(verticalAlignment = Alignment.CenterVertically) {

            Icon(
                painter = painterResource(id = R.drawable.manat),
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = price,
                fontSize = 18.sp,
            )
        }
    }

    Divider(
        Modifier
            .background(Color.Gray.copy(alpha = .5f))
            .height(4.dp)
    )
    Spacer(modifier = Modifier.height(16.dp))

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

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 16.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.location_icon),
            contentDescription = null,
            tint = MaterialTheme.colors.primary,
            modifier = Modifier.size(30.dp),
        )
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = locationName,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground,
                fontSize = 20.sp
            )
            Text(
                text = locationName,
                color = MaterialTheme.colors.onBackground.copy(alpha = .5f),
                fontSize = 14.sp,
            )
        }

    }
}


@Composable
private fun UserField(user: User) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 16.dp)
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
    Divider(Modifier.padding(top = 8.dp))
    BBCTextButton(text = "Keanu Reeves kullanıcısına ulaş") {
        
    }
}
