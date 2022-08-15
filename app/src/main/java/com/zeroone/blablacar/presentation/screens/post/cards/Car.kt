package com.zeroone.blablacar.presentation.screens.post.cards

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zeroone.blablacar.R
import com.zeroone.blablacar.domain.model.Car
import com.zeroone.blablacar.domain.model.Post
import com.zeroone.blablacar.ui.cards.*


@Composable
fun Car(car: Car) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        BBCText(
            text = stringResource(id = R.string.car),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        
        
        BBCText(
            text = car.name,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}

@Composable
fun CarExtended(car: Car) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {

        BBCText(text = stringResource(id = R.string.car), fontWeight = FontWeight.Bold, fontSize = 18.sp)

        Spacer(modifier = Modifier.height(8.dp))

        BBCText(text = car.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)

        BBCDivider()

        BBCText(text = "${stringResource(id = R.string.max)} ${car.maxPerson} ${stringResource(id = R.string.person)}")
        BBCText(text = stringResource(id = R.string.no_smoking))
        BBCText(text = stringResource(id = R.string.pet_allowed))

        BBCDivider()
    }
}


