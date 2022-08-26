package com.zeroone.blablacar.presentation.screens.home

import android.util.Log
import androidx.compose.foundation.layout.*
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
import com.zeroone.blablacar.domain.model.postList
import com.zeroone.blablacar.presentation.screens.home.components.Search
import com.zeroone.blablacar.presentation.ui.cards.BBCListView

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    backOnClick: () -> Unit,
    postOnClick: (Int) -> Unit,
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
            Content(modifier.padding(it), postOnClick)
        },
    )

}

@Composable
private fun Content(
    modifier: Modifier,
    postClick: (Int) -> Unit
) {
    Column(modifier = modifier) {

        val postList = remember { mutableStateOf(postList) }

        Spacer(modifier = Modifier.height(8.dp))
        //Search()

        BBCListView(postList = postList.value)
    }
}

