package com.zeroone.blablacar.presentation.screens

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.zeroone.blablacar.R
import com.zeroone.blablacar.presentation.BBCState
import com.zeroone.blablacar.utils.TAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.annotation.concurrent.Immutable

sealed class Screen(val route: String) {
    @Immutable
    object Registration : Screen("registration")

    @Immutable
    object Login : Screen("login")

    @Immutable
    object Home : Screen("home")

    @Immutable
    object Post : Screen("post")

    @Immutable
    object Profile : Screen("profile")

    @Immutable
    object EditProfile : Screen("editProfile")
}
