package com.zeroone.blablacar.presentation.screens.main

import androidx.annotation.StringRes
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.zeroone.blablacar.R
import com.zeroone.blablacar.presentation.screens.new_post.navigation.NewPostRoutes
import javax.annotation.concurrent.Immutable


sealed class BottomNavItem(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector
) {
    @Immutable
    object Search : BottomNavItem(Routes.Home.route, R.string.search, Icons.Default.Search)

    @Immutable
    object NewPost : BottomNavItem(Routes.NewPost.route, R.string.new_post, Icons.Rounded.AddCircle)

    @Immutable
    object MyTravels :
        BottomNavItem(Routes.Profile.route, R.string.my_travels, Icons.Default.Person)

    @Immutable
    object Inbox : BottomNavItem(Routes.Profile.route, R.string.inbox, Icons.Default.Email)

    @Immutable
    object Profile : BottomNavItem(Routes.Profile.route, R.string.profile, Icons.Default.Person)

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


    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    if (currentDestination?.parent?.route != Graph.NEW_POST ) {
        BottomNavigation(backgroundColor = MaterialTheme.colors.background) {
            items.forEach { item ->
                BottomNavigationItem(
                    icon = { Icon(imageVector = item.icon, contentDescription = null) },
                    //label = { Text(stringResource(item.resourceId)) },
                    selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                    selectedContentColor = MaterialTheme.colors.primary,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(
                                navController.graph
                                    .findStartDestination().id
                            ) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

