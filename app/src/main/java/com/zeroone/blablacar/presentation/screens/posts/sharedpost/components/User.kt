package com.zeroone.blablacar.presentation.screens.posts.sharedpost.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zeroone.blablacar.R
import com.zeroone.blablacar.domain.model.User
import com.zeroone.blablacar.presentation.ui.cards.*

@Composable
fun User(user: User) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        BBCUserImage(painterResource(id = R.drawable.car), 50.dp, 50.dp)

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            BBCText(
                text = "${user.name} ${user.surname}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Row(verticalAlignment = Alignment.CenterVertically) {

                BBCImage(imageVector = Icons.Default.Star)
                BBCText(text = user.getRate().toString())
                BBCText(text = "(${user.getRateSize()} ${stringResource(id = R.string.rate)})", fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun UserExtended(user: User) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically){

            BBCUserImage(painterResource(id = R.drawable.car), 75.dp, 75.dp)

            Column(modifier = Modifier.padding(horizontal = 8.dp),
                horizontalAlignment = Alignment.Start
            ) {

                BBCBoldText(text = "${user.name} ${user.surname}")

                BBCText(text = "${stringResource(id = R.string.gender)} : Male")

                BBCText(text = "${stringResource(id = R.string.age)} : 35")

            }
        }

        BBCDivider()

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly){

            Indicator(user = user, title = stringResource(id = R.string.drive), imageVector = Icons.Default.Person)
            Indicator(user = user, title = stringResource(id = R.string.general), imageVector = Icons.Default.Home)
        }
        
        BBCDivider()

        BBCBoldText(text = stringResource(id = R.string.about))
        BBCText(text = stringResource(id = R.string.lorem))

        BBCDivider()


    }
}

@Composable
private fun Indicator(user: User, title: String, imageVector: ImageVector) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            BBCImage(imageVector = imageVector)
            BBCText(text = title)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {

            BBCImage(imageVector = Icons.Default.Star)
            BBCText(text = user.getRate().toString())
            BBCText(text = "(${user.getRateSize()} ${stringResource(id = R.string.rate)})", fontSize = 12.sp)
        }
    }
}

