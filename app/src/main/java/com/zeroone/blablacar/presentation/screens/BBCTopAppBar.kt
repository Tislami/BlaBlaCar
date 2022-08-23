package com.zeroone.blablacar.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.zeroone.blablacar.presentation.BBCState

@Composable
fun BBCTopAppBar(bbcState: BBCState) {

    when (bbcState.navController.currentBackStackEntryAsState().value?.destination?.route){
        Screen.Registration.route->{ RegistrationTopAppBar()}
        Screen.Login.route->{ LoginTopAppBar() }
    }
}

@Composable
fun HomeTopAppBar() {
    TopAppBar(
        backgroundColor = Color.Transparent,
        contentColor = Color.Transparent,
        elevation = 0.dp
    ) {

    }
}

@Composable
fun RegistrationTopAppBar() {
    TopAppBar() {
        Row(Modifier.background(Color.Yellow)){
            Icon(imageVector = Icons.Default.Home, contentDescription = null)
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
            Icon(imageVector = Icons.Default.Person, contentDescription = null)
            Text(text = "Registration")
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
    }
}

@Composable
fun LoginTopAppBar() {
    TopAppBar() {
        Row(Modifier.background(Color.Green)){
            Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null)
            Icon(imageVector = Icons.Default.Face, contentDescription = null)
            Icon(imageVector = Icons.Default.Person, contentDescription = null)
            Text(text = "Login")
            Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
        }
    }
}

