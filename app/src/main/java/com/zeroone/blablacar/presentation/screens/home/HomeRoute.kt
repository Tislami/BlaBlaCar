package com.zeroone.blablacar.presentation.screens.home


import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.zeroone.blablacar.R
import com.zeroone.blablacar.domain.model.User
import com.zeroone.blablacar.domain.model.defaultUser
import com.zeroone.blablacar.presentation.screens.home.components.HomeSelectionButton
import com.zeroone.blablacar.presentation.screens.home.components.HomeTopAppBar
import com.zeroone.blablacar.presentation.ui.cards.PostCard
import com.zeroone.blablacar.presentation.ui.components.BBCTextField
import kotlinx.coroutines.launch

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
) {
    HomeScreen(modifier = modifier)
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
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
    date: String,
    personSize: Int,
    personSizeOnClick: () -> Unit,
    dateOnClick: () -> Unit,
    searchOnClick: () -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
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

        items(15) {
            PostCard(
                dateTime = "Pzt 25 Tem, 20:00",
                fromLocation = "Eskişehir",
                toLocation = "İstanbul",
                user = defaultUser,
                onClick = {},
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun Head(
    date: String,
    dateOnClick: () -> Unit,
    personSize: Int,
    personSizeOnClick: () -> Unit,
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
                .background(MaterialTheme.colors.onSecondary.copy(alpha = .25f))
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
                    .width(2.dp)
            )

            HomeSelectionButton(
                modifier = Modifier.weight(.30f),
                text = personSize.toString(),
                icon = Icons.Default.Person,
                onClick = personSizeOnClick
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
                maxLines= 1,
                style = MaterialTheme.typography.button
            )
        }
    }
}
