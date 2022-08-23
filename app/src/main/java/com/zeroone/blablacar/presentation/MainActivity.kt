package com.zeroone.blablacar.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.zeroone.blablacar.presentation.screens.BBCNavigation
import com.zeroone.blablacar.presentation.ui.theme.BlaBlaCarTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlaBlaCarTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {


    BBCNavigation()
}