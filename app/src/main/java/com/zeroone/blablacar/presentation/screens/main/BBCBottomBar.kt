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
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
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
    object NewPost : BottomNavItem(Screen.NewPost.route, R.string.add, Icons.Default.Add)
    @Immutable
    object Profile : BottomNavItem(Screen.Profile.route, R.string.profile, Icons.Default.Person)
}

/*
@NonRestartableComposable
@Composable
fun BBCBottomBar(screenState: ScreenState,currentDestination: NavBackStackEntry?) {

    Log.d("Screen", "BBCBottomBar: ")

    BottomAppBar(modifier = Modifier
        .padding(4.dp)
        .clip(MaterialTheme.shapes.medium)) {
        BottomNavigation {
            screenState.getBottomBarNavItems.forEach { item ->
                BottomNavigationItem(
                    icon = { Icon(item.icon, contentDescription = null) },
                    label = { Text(stringResource(id = item.resourceId)) },
                    selected = currentDestination?.destination?.hierarchy?.any { it.route == item.route } == true,
                    onClick = {
                        screenState.navController.navigate(item.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(screenState.navController.graph.findStartDestination().id) {
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
*/

