package com.zeroone.blablacar.presentation.screens.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zeroone.blablacar.R
import com.zeroone.blablacar.domain.model.defaultPost
import com.zeroone.blablacar.domain.model.postList
import com.zeroone.blablacar.presentation.screens.home.components.Search
import com.zeroone.blablacar.presentation.screens.main.BBCBottomBar
import com.zeroone.blablacar.presentation.screens.post.cards.Post
import kotlin.random.Random

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    backOnClick: () -> Unit,
    postClick: (Int) -> Unit,
) {

    Log.d("Screen", "HomeScreen: ")


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.home))},
                navigationIcon = {
                    IconButton(onClick = backOnClick) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        content = {
            Content(modifier.padding(it), backOnClick, postClick)
        },
    )

}

@Composable
private fun Content(
    modifier: Modifier,
    backOnClick: () -> Unit,
    postClick: (Int) -> Unit
) {
    Column(modifier = modifier) {

        val postList = remember { mutableStateOf(postList) }

        Spacer(modifier = Modifier.height(8.dp))
        Search()

        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(8.dp)
        ) {
            items(postList.value) {
                Post(post = defaultPost, modifier = Modifier.clickable {
                    postClick(defaultPost.id)
                })
            }

        }
    }
}

