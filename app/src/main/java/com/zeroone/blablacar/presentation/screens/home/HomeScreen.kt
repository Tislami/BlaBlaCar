package com.zeroone.blablacar.presentation.screens.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zeroone.blablacar.domain.model.defaultPost
import com.zeroone.blablacar.presentation.screens.main.BBCBottomBar
import com.zeroone.blablacar.presentation.screens.post.cards.Post

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    backOnClick: () -> Unit,
    postClick: (Int) -> Unit, ) {

    Log.d("Screen", "HomeScreen: ")


    Scaffold(
        topBar = {
            TopAppBar() {
                Text(text = "Home")
            }
        },
        content = {
            Content(modifier, backOnClick, postClick)
        },
    )

}

@Composable
private fun Content(
    modifier: Modifier,
    backOnClick: () -> Unit,
    postClick: (Int) -> Unit
) {
    Column() {


        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(8.dp)
        ) {
            item {
                Button(onClick = backOnClick) {
                }
            }

            items(500) {
                Post(post = defaultPost, modifier = Modifier.clickable {
                    postClick(defaultPost.id)
                })
            }
        }
    }
}

