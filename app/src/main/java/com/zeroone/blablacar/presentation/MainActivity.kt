package com.zeroone.blablacar.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.zeroone.blablacar.presentation.screens.auth.AuthScreen
import com.zeroone.blablacar.presentation.screens.auth.login.LoginScreen
import com.zeroone.blablacar.presentation.screens.auth.registration.RegistrationScreen
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