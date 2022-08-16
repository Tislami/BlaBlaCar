package com.zeroone.blablacar.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.zeroone.blablacar.domain.model.defaultPost
import com.zeroone.blablacar.presentation.screens.Screens
import com.zeroone.blablacar.presentation.screens.home.components.Search
import com.zeroone.blablacar.presentation.screens.post.cards.Post

@Composable
fun HomeScreen(navController: NavHostController) {

    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        content = {
            item { Search() }
            items(10) {
                Post(post = defaultPost) {navController.navigate(Screens.Post.route+ "/${defaultPost.id}")} }
        }
    )
}

