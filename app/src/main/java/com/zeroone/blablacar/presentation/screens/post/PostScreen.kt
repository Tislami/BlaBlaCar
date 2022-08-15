package com.zeroone.blablacar.presentation.screens.post

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zeroone.blablacar.domain.model.Post
import com.zeroone.blablacar.presentation.screens.post.components.*
import com.zeroone.blablacar.ui.cards.*

@Composable
fun PostScreen(post: Post) {
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val isUserExtended = remember { mutableStateOf(false) }
        val isCarExtended = remember { mutableStateOf(false) }

        BBCCard(modifier = Modifier.fillMaxWidth(),
            onClick = { isUserExtended.value = !isUserExtended.value }) {
            if (isUserExtended.value) UserExtended(post.user)
            else User(post.user)
        }

        BBCCard(
            modifier = Modifier.fillMaxWidth(),
            onClick = { isCarExtended.value = !isCarExtended.value }) {
            if (isCarExtended.value) CarExtended(post.car)
            else Car(post.car)
        }

        BBCCard(modifier = Modifier.fillMaxWidth(), {}) { LocationAndDate(post) }

        BBCCard(modifier = Modifier.fillMaxWidth(), {}) { Payment(post) }

    }
}


