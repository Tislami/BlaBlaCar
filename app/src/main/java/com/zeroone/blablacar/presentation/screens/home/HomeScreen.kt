package com.zeroone.blablacar.presentation.screens.home

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.zeroone.blablacar.domain.model.defaultPost
import com.zeroone.blablacar.presentation.screens.home.components.Search
import com.zeroone.blablacar.ui.cards.BBCButton
import com.zeroone.blablacar.ui.cards.BBCTextField
import com.zeroone.blablacar.presentation.screens.post.cards.Post
import com.zeroone.blablacar.ui.cards.BBCCard
import com.zeroone.blablacar.ui.cards.BBCText

@Composable
fun HomeScreen() {

    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        content = {
            item { Search() }
            items(10) { Post(post = defaultPost) {} }
        }
    )
}
