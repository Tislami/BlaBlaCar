package com.zeroone.blablacar.presentation.screens.post

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zeroone.blablacar.domain.model.Post
import com.zeroone.blablacar.domain.model.defaultPost
import com.zeroone.blablacar.presentation.screens.post.components.*
import com.zeroone.blablacar.presentation.ui.cards.*
import com.zeroone.blablacar.utils.TAG

@Composable
fun PostScreen() {

    Log.d("Screen", "PostScreen: ")
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val isUserExtended = remember { mutableStateOf(false) }
        val isCarExtended = remember { mutableStateOf(false) }

        BBCCard(modifier = Modifier.fillMaxWidth()
            .clickable { isUserExtended.value = !isUserExtended.value }) {
            if (isUserExtended.value) UserExtended(defaultPost.user)
            else User(defaultPost.user)
        }

        BBCCard(modifier = Modifier
            .fillMaxWidth()
            .clickable {isCarExtended.value = !isCarExtended.value}) {
            if (isCarExtended.value) CarExtended(defaultPost.car)
            else Car(defaultPost.car)
        }

        BBCCard(modifier = Modifier.fillMaxWidth()) { LocationAndDate(defaultPost) }

        BBCCard(modifier = Modifier.fillMaxWidth()) { Payment(defaultPost) }

    }
}


