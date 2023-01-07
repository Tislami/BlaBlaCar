package com.zeroone.blablacar.presentation.screens.new_post.contents

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zeroone.blablacar.presentation.screens.new_post.NewPostState

@Composable
fun CustomerAndPriceContent(
    modifier: Modifier= Modifier,
    newPostState: NewPostState,
) {
    Column(modifier = modifier){
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 500.dp, max = 750.dp),
            shape = MaterialTheme.shapes.medium,
        ) {
            Column(){
                for (i in 0..5){
                    Row() {
                        Text(text =" adskjnfkjsd ")

                    }
                    Text(text =" dalkfns")
                }
            }

            /*newPostState.direction?.routes?.forEach {
                it.legs.forEach {
                    Text(text =" ${it.start_address}  ")
                    Text(text =" ${it.end_address}")
                }
            }*/
        }
    }
}