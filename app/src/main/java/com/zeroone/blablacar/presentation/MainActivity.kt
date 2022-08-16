package com.zeroone.blablacar.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.zeroone.blablacar.presentation.screens.BBCNavigation
import com.zeroone.blablacar.presentation.screens.BBCScaffold
import com.zeroone.blablacar.presentation.screens.home.HomeTopAppBar
import com.zeroone.blablacar.ui.theme.BlaBlaCarTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlaBlaCarTheme {
                BBCNavigation()
            }
        }
    }
}