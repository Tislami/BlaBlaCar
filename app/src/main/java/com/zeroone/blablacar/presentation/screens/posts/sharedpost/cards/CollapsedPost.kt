package com.zeroone.blablacar.presentation.screens.posts.sharedpost.cards

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zeroone.blablacar.R
import com.zeroone.blablacar.domain.model.Post
import com.zeroone.blablacar.presentation.ui.cards.*

@Composable
fun CollapsedPost(
    modifier: Modifier = Modifier,
    post: Post,
    index: String,
    enterTransition: EnterTransition
) {

    val visibleState = MutableTransitionState(false).apply {
        targetState= true
    }

    AnimatedVisibility(
        visibleState = visibleState,
        enter = enterTransition
    ) {
        BBCCard {
            Column(
                modifier = modifier.padding(8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = index)
                Location(post)
                Spacer(modifier = Modifier.height(8.dp))
                Date(post)
                Spacer(modifier = Modifier.height(8.dp))
                PersonAndPrice(post)
            }
        }
    }
}

@Composable
private fun Location(post: Post) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)

        Spacer(modifier = Modifier.width(4.dp))

        BBCPostText(text = post.from)
        Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
        BBCPostText(text = post.to)
    }
}

@Composable
private fun Date(post: Post) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(4.dp))
            BBCPostText(text = post.date)
        }

        Row {
            Icon(
                painter = painterResource(id = R.drawable.clock_logo),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(4.dp))
            BBCPostText(text = post.time)
        }

    }
}

@Composable
private fun PersonAndPrice(post: Post) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(4.dp))

            BBCPostText(text = "${post.car.emptySeat} ${stringResource(id = R.string.empty_seat)}")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            BBCPostText(text = post.price.toString())
            Spacer(modifier = Modifier.width(4.dp))
            BBCCardImage(
                painterResource(id = R.drawable.manat),
                20.dp,
                20.dp,
                shape = RectangleShape
            )
        }
    }

}



