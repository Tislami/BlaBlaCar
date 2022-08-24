package com.zeroone.blablacar.presentation.screens.main

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.zeroone.blablacar.R
import javax.annotation.concurrent.Immutable


sealed class BottomNavItem(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector
) {
    @Immutable
    object Home : BottomNavItem(Screen.Home.route, R.string.home, Icons.Default.Home)
    @Immutable
    object Post : BottomNavItem(Screen.Login.route, R.string.add, Icons.Default.Add)
    @Immutable
    object Profile : BottomNavItem(Screen.Profile.route, R.string.profile, Icons.Default.Person)
}

val bottomNavItems = listOf(
    BottomNavItem.Home,
    BottomNavItem.Post,
    BottomNavItem.Profile,
)


@Composable
fun BBCBottomBar(bbcState: BBCState) {

    Log.d("Screen", "BBCBottomBar: ")

    BottomAppBar(modifier = Modifier
        .padding(4.dp)
        .clip(MaterialTheme.shapes.medium)) {
        BottomNavigation {
            bbcState.getBottomBarNavItems.forEach { item ->
                BottomNavigationItem(
                    icon = { Icon(item.icon, contentDescription = null) },
                    label = { Text(stringResource(id = item.resourceId)) },
                    selected = bbcState.GetCurrentDestination()?.hierarchy?.any { it.route == item.route } == true,
                    onClick = {
                        bbcState.navController.navigate(item.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(bbcState.navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = false
                            // Restore state when reselecting a previously selected item
                            restoreState = false
                        }
                    }
                )
            }
        }
    }
}

