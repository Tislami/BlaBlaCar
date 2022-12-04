package com.zeroone.blablacar.presentation.screens.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.zeroone.blablacar.domain.model.User
import com.zeroone.blablacar.domain.model.defaultUser
import com.zeroone.blablacar.presentation.screens.home.components.HomeSelectionButton
import com.zeroone.blablacar.presentation.screens.home.components.HomeTopAppBar
import com.zeroone.blablacar.presentation.ui.cards.PostCard
import com.zeroone.blablacar.presentation.ui.components.BBCTextField

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

    Scaffold(
        topBar = { HomeTopAppBar() },
        content = {
            HomeContent(
                modifier = modifier.padding(it),
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
    date: String,
    personSize: Int,
    personSizeOnClick: () -> Unit,
    dateOnClick: () -> Unit,
    searchOnClick: () -> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {

        Head(
            date,
            dateOnClick,
            personSize,
            personSizeOnClick,
            searchOnClick
        )
        LazyColumn(
            modifier = Modifier.padding(vertical = 16.dp),
        ) {
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
}

@Composable
private fun Head(
    date: String,
    dateOnClick: () -> Unit,
    personSize: Int,
    personSizeOnClick: () -> Unit,
    searchOnClick: () -> Unit
) {
    BBCTextField(value = "", onValueChange = {}, labelText = "Kalkış yeri")
    BBCTextField(value = "", onValueChange = {}, labelText = "Varış yeri")

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
                text = "Ara",
                style = MaterialTheme.typography.button
            )
        }
    }
}
