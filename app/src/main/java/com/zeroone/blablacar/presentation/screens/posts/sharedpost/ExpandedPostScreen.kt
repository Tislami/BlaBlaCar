package com.zeroone.blablacar.presentation.screens.posts.sharedpost

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zeroone.blablacar.domain.model.defaultPost
import com.zeroone.blablacar.presentation.screens.posts.sharedpost.components.*
import com.zeroone.blablacar.presentation.ui.cards.*

@Composable
fun ExpandedPostScreen() {

    Log.d("Screen", "ExpandedPostScreen: ")
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        BBCCard(modifier = Modifier.fillMaxWidth()) {
            UserExtended(defaultPost.user)
        }

        BBCCard(modifier = Modifier.fillMaxWidth()) {
            CarExtended(defaultPost.car)
        }

        BBCCard(modifier = Modifier.fillMaxWidth()) { LocationAndDate(defaultPost) }

        BBCCard(modifier = Modifier.fillMaxWidth()) { Payment(defaultPost) }

    }
}


