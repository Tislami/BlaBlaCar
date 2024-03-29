package com.zeroone.blablacar.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.maps.android.compose.GoogleMap
import com.zeroone.blablacar.presentation.screens.main.BBCNavigation
import com.zeroone.blablacar.presentation.ui.theme.BlaBlaCarTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BlaBlaCarTheme(darkTheme = false) {

             Main()
            }
        }
    }
}

@Composable
fun Main() {

    BBCNavigation()
}