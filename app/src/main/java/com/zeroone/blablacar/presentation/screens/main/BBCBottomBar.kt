package com.zeroone.blablacar.presentation.screens.main

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.zeroone.blablacar.R
import javax.annotation.concurrent.Immutable


sealed class BottomNavItem(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector
) {
    @Immutable
    object Search : BottomNavItem(Screen.Home.route, R.string.search, Icons.Default.Search)

    @Immutable
    object NewPost : BottomNavItem(Screen.NewPost.route, R.string.new_post, Icons.Rounded.Add)

    @Immutable
    object MyTravels : BottomNavItem(Screen.Profile.route, R.string.my_travels, Icons.Default.Person)

    @Immutable
    object Inbox : BottomNavItem(Screen.Profile.route, R.string.inbox, Icons.Default.Send)

    @Immutable
    object Profile : BottomNavItem(Screen.Profile.route, R.string.profile, Icons.Default.Person)

}

@NonRestartableComposable
@Composable
fun BBCBottomBar(
    navController: NavHostController
) {

    val items = listOf(
        BottomNavItem.Search,
        BottomNavItem.NewPost,
        BottomNavItem.MyTravels,
        BottomNavItem.Inbox,
        BottomNavItem.Profile,
    )

    BottomNavigation(
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = null) },
                //label = { Text(stringResource(item.resourceId)) },
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                onClick = {/*
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }*/
                }
            )
        }
    }
}

