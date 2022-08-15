package com.zeroone.blablacar.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.zeroone.blablacar.presentation.screens.home.HomeScreen
import com.zeroone.blablacar.ui.theme.BlaBlaCarTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlaBlaCarTheme {
                HomeScreen()
            }
        }
    }
}