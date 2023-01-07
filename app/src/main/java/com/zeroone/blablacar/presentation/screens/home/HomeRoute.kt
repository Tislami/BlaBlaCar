package com.zeroone.blablacar.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zeroone.blablacar.R
import com.zeroone.blablacar.domain.model.Post2
import com.zeroone.blablacar.presentation.screens.home.components.HomeSelectionButton
import com.zeroone.blablacar.presentation.screens.home.components.HomeTopAppBar
import com.zeroone.blablacar.presentation.ui.cards.PostCard
import com.zeroone.blablacar.presentation.ui.components.BBCTextField
import kotlinx.coroutines.launch

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    HomeScreen(
        modifier = modifier,
        postState = homeViewModel.postState.value
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    postState: List<Post2>,
) {

    val listState: LazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val showButton by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 0 }
    }


    Scaffold(
        topBar = {
            HomeTopAppBar(
                showButton = showButton,
                onClick = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(0)
                    }
                }
            )
        },
        content = {
            HomeContent(
                modifier = modifier.padding(it),
                listState = listState,
                postState=postState,
                date = "Bugün",
                personSize = 5,
                dateOnClick = {},
                personSizeOnClick = {},
                searchOnClick = {}
            )
        },
    )
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    listState: LazyListState,
    postState: List<Post2>,
    date: String,
    personSize: Int,
    personSizeOnClick: () -> Unit,
    dateOnClick: () -> Unit,
    searchOnClick: () -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        state = listState,
    ) {

        item {
            Head(
                date,
                dateOnClick,
                personSize,
                personSizeOnClick,
                searchOnClick
            )

            Spacer(modifier = Modifier.height(16.dp))

        }
        items(postState) {post->
            PostCard(
                post = post,
                onClick = {},
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
private fun Head(
    date: String,
    dateOnClick: () -> Unit,
    personCount: Int,
    personCountOnClick: () -> Unit,
    searchOnClick: () -> Unit
) {
    BBCTextField(value = "", onValueChange = {}, labelText = stringResource(id = R.string.from))
    BBCTextField(value = "", onValueChange = {}, labelText = stringResource(id = R.string.to))

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .weight(.5f)
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colors.surface)
                .fillMaxWidth()
                .height(55.dp)
        ) {

            HomeSelectionButton(
                modifier = Modifier.weight(.60f),
                text = date,
                icon = Icons.Default.DateRange,
                onClick = dateOnClick
            )

            Divider(
                Modifier
                    .fillMaxHeight()
                    .padding(vertical = 8.dp)
                    .width(2.dp),
            )

            HomeSelectionButton(
                modifier = Modifier.weight(.30f),
                text = personCount.toString(),
                icon = Icons.Default.Person,
                onClick = personCountOnClick
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = searchOnClick,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .weight(.15f)
                .height(55.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = stringResource(id = R.string.search),
                maxLines = 1,
                style = MaterialTheme.typography.button
            )
        }
    }
}
