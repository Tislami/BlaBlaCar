package com.zeroone.blablacar.presentation.ui.cards

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.unit.dp
import com.zeroone.blablacar.domain.model.Post
import com.zeroone.blablacar.presentation.screens.posts.sharedpost.cards.CollapsedPost

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BBCListView(postList: List<Post>) {

    Log.d("BBcAnimat", "BBCListView: Screen")

    val lazyListState = rememberLazyListState()

    val enterTransition: EnterTransition = scaleIn(
        animationSpec = tween(1000),
        0f,
        TransformOrigin.Center
    )

    LazyColumn(
        state = lazyListState,
        contentPadding = PaddingValues(8.dp),
    ) {
        items(
            count = postList.size,
            key = { postList[it].id + it },
            itemContent = { index ->
                CollapsedPost(
                    post = postList[index],
                    index = index.toString(),
                    enterTransition = enterTransition
                )
            }
        )
    }
}