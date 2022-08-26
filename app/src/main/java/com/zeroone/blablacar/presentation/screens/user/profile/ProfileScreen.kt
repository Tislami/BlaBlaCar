package com.zeroone.blablacar.presentation.screens.user.profile

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.motion.widget.MotionScene
import com.zeroone.blablacar.R
import com.zeroone.blablacar.domain.model.User
import com.zeroone.blablacar.domain.model.defaultPost
import com.zeroone.blablacar.presentation.screens.home.components.Search
import com.zeroone.blablacar.presentation.screens.user.profile.components.ProfileTopBar
import com.zeroone.blablacar.presentation.ui.cards.BBCBoldText
import com.zeroone.blablacar.presentation.ui.cards.BBCImage
import com.zeroone.blablacar.presentation.ui.cards.BBCText
import com.zeroone.blablacar.presentation.ui.cards.BBCUserImage
import com.zeroone.blablacar.utils.TAG

@Stable
@Composable
fun ProfileScreen(
    user: User,
    backOnClick: () -> Unit,
    settingOnClick: () -> Unit,
) {
    Log.d("Screen", "ProfileScreen: ")

    Scaffold(
        topBar = { ProfileTopBar(user, backOnClick, settingOnClick) },
        content = {
            Column() {
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
        },
        bottomBar = {}
    )
}
