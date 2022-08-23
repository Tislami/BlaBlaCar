package com.zeroone.blablacar.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zeroone.blablacar.presentation.ui.cards.BBCCard
import com.zeroone.blablacar.presentation.ui.cards.BBCText
import com.zeroone.blablacar.presentation.ui.cards.BBCTextField

@Composable
fun Search() {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = Color(33, 150, 243, 255),
        elevation = 8.dp
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 8.dp)
        ) {

            BBCTextField(text = "", onValueChange = {}, hint = "From")
            BBCTextField(text = "", onValueChange = {}, hint = "To")

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Date()

                BBCCard{
                    Row(
                        modifier = Modifier
                            .background(MaterialTheme.colors.primary)
                            .height(50.dp)
                            .width(100.dp)
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                        BBCText(text = "Search")
                    }
                }
            }
        }
    }
}

@Composable
private fun Date() {

    BBCCard{
        Row(
            modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .height(50.dp)
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(imageVector = Icons.Default.DateRange, contentDescription = "")

            Spacer(modifier = Modifier.width(8.dp))

            BBCText(text = "05")

            Spacer(modifier = Modifier.width(4.dp))

            BBCText(text = "SEPTEMBER")
        }
    }
}