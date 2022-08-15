package com.zeroone.blablacar.presentation.screens.post.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zeroone.blablacar.R
import com.zeroone.blablacar.domain.model.Car
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


