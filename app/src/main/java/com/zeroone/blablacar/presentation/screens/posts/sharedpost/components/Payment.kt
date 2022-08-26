package com.zeroone.blablacar.presentation.screens.posts.sharedpost.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zeroone.blablacar.R
import com.zeroone.blablacar.domain.model.Post
import com.zeroone.blablacar.presentation.ui.cards.BBCButton
import com.zeroone.blablacar.presentation.ui.cards.BBCCardImage
import com.zeroone.blablacar.presentation.ui.cards.BBCPostText
import com.zeroone.blablacar.presentation.ui.cards.BBCText

@Composable
fun Payment(post: Post) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            BBCText(
                text = stringResource(id = R.string.payment),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                BBCText(
                    text = "${stringResource(id = R.string.one_person_price)} : ",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    BBCPostText(text = post.price.toString())
                    Spacer(modifier = Modifier.width(4.dp))
                    BBCCardImage(
                        painterResource(id = R.drawable.manat),
                        20.dp,
                        20.dp,
                        shape = RectangleShape
                    )
                }
            }
        }

        BBCButton(text = stringResource(id = R.string.book), onClick = { /*TODO*/ })
    }
}