package com.zeroone.blablacar.presentation.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.zeroone.blablacar.domain.model.defaultPost
import com.zeroone.blablacar.presentation.rememberBBCState
import com.zeroone.blablacar.presentation.screens.BBCTopAppBar
import com.zeroone.blablacar.presentation.screens.Screen
import com.zeroone.blablacar.presentation.screens.post.cards.Post
import com.zeroone.blablacar.utils.TAG

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    backOnClick: () -> Unit,
    postClick:(Int) -> Unit
) {


    Column(){


    Log.d(TAG, "HomeScreen: ")
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp)
    ) {
        item {
            Button(onClick = backOnClick) {
            }
        }

        items(10) {
            Post(post = defaultPost, modifier = Modifier.clickable {
                postClick(defaultPost.id)
            })
        }
    }
    }
}

