package com.zeroone.blablacar.presentation.screens.new_post.navigation

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.zeroone.blablacar.presentation.screens.main.Graph
import com.zeroone.blablacar.presentation.screens.new_post.NewPostViewModel
import com.zeroone.blablacar.presentation.screens.new_post.routes.*
import javax.annotation.concurrent.Immutable


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.newPostNavigation(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    navigation(
        route = Graph.NEW_POST,
        startDestination = NewPostRoutes.From.route,
        exitTransition = {
            slideOutVertically(
                animationSpec = tween(1000),
                targetOffsetY = { -2000 }
            )
        }
    ) {
        composable(
            route = NewPostRoutes.From.route,
            enterTransition = {
                if (navController.previousBackStackEntry?.destination?.route == NewPostRoutes.To.route) {
                    slideInHorizontally(
                        animationSpec = tween(1000),
                        initialOffsetX = { 2000 }
                    )
                } else {
                    slideInVertically(
                        animationSpec = tween(1000),
                        initialOffsetY = { 2000 }
                    )
                }
            },
            exitTransition = {
                if (navController.currentBackStackEntry?.destination?.route == NewPostRoutes.To.route) {
                    slideOutHorizontally(
                        animationSpec = tween(1000),
                        targetOffsetX = { 2000 }
                    )
                } else {
                    slideOutVertically(
                        animationSpec = tween(1000),
                        targetOffsetY = { -2000 }
                    )
                }
            }
        ) { backStackEntry ->
            val parentEntry =
                remember(backStackEntry) { navController.getBackStackEntry(Graph.NEW_POST) }
            val parentViewModel = hiltViewModel<NewPostViewModel>(parentEntry)
            FromRoute(
                modifier = modifier,
                navController = navController,
                newPostViewModel = parentViewModel
            )
        }

        composable(
            route = NewPostRoutes.To.route,
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(1000),
                    initialOffsetX = {
                        if (navController.previousBackStackEntry?.destination?.route == NewPostRoutes.From.route)
                            -2000 else { 2000 }
                    }
                )
            },
            exitTransition = {
                if (navController.currentBackStackEntry?.destination?.route == NewPostRoutes.From.route) {
                    slideOutHorizontally(
                        animationSpec = tween(1000),
                        targetOffsetX = { -2000 }
                    )
                } else {
                    slideOutHorizontally(
                        animationSpec = tween(1000),
                        targetOffsetX = { 2000 }
                    )
                }
            }
        ) { backStackEntry ->
            val parentEntry =
                remember(backStackEntry) { navController.getBackStackEntry(Graph.NEW_POST) }
            val parentViewModel = hiltViewModel<NewPostViewModel>(parentEntry)
            ToRoute(
                modifier = modifier,
                navController = navController,
                newPostViewModel = parentViewModel
            )
        }

        composable(
            route = NewPostRoutes.Direction.route,
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(1000),
                    initialOffsetX = {
                        if (navController.previousBackStackEntry?.destination?.route == NewPostRoutes.To.route)
                            -2000 else { 2000 }
                    }
                )
            },
            exitTransition = {
                if (navController.currentBackStackEntry?.destination?.route == NewPostRoutes.To.route) {
                    slideOutHorizontally(
                        animationSpec = tween(1000),
                        targetOffsetX = { -2000 }
                    )
                } else {
                    slideOutHorizontally(
                        animationSpec = tween(1000),
                        targetOffsetX = { 2000 }
                    )
                }
            }
        ) { backStackEntry ->
            val parentEntry =
                remember(backStackEntry) { navController.getBackStackEntry(Graph.NEW_POST) }
            val parentViewModel = hiltViewModel<NewPostViewModel>(parentEntry)
            DirectionRoute(
                modifier = modifier,
                navController = navController,
                newPostViewModel = parentViewModel
            )
        }

        composable(
            route = NewPostRoutes.AddLocation.route,
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(1000),
                    initialOffsetX = {
                        if (navController.previousBackStackEntry?.destination?.route == NewPostRoutes.Direction.route)
                            -2000 else { 2000 }
                    }
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    animationSpec = tween(1000),
                    targetOffsetX = { -2000 }
                )
            }
        ) { backStackEntry ->
            val parentEntry =
                remember(backStackEntry) { navController.getBackStackEntry(Graph.NEW_POST) }
            val parentViewModel = hiltViewModel<NewPostViewModel>(parentEntry)
            AddLocationRoute(
                modifier = modifier,
                navController = navController,
                newPostViewModel = parentViewModel
            )
        }

        composable(
            route = NewPostRoutes.Price.route,
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(1000),
                    initialOffsetX = {
                        if (navController.previousBackStackEntry?.destination?.route == NewPostRoutes.PersonCount.route)
                            -2000 else { 2000 }
                    }
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    animationSpec = tween(1000),
                    targetOffsetX = { -2000 }
                )
            }
        ) { backStackEntry ->
            val parentEntry =
                remember(backStackEntry) { navController.getBackStackEntry(Graph.NEW_POST) }
            val parentViewModel = hiltViewModel<NewPostViewModel>(parentEntry)
            PriceRoute(
                modifier = modifier,
                navController = navController,
                newPostViewModel = parentViewModel
            )
        }

        composable(
            route = NewPostRoutes.DateTime.route,
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(1000),
                    initialOffsetX = { 2000 }
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    animationSpec = tween(1000),
                    targetOffsetX = { -2000 }
                )
            }
        ) { backStackEntry ->
            val parentEntry =
                remember(backStackEntry) { navController.getBackStackEntry(Graph.NEW_POST) }
            val parentViewModel = hiltViewModel<NewPostViewModel>(parentEntry)
            DateTimeRoute(
                modifier = modifier,
                navController = navController,
                newPostViewModel = parentViewModel
            )
        }

        composable(
            route = NewPostRoutes.PersonCount.route,
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(1000),
                    initialOffsetX = { 2000 }
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    animationSpec = tween(1000),
                    targetOffsetX = { -2000 }
                )
            }
        ) { backStackEntry ->
            val parentEntry =
                remember(backStackEntry) { navController.getBackStackEntry(Graph.NEW_POST) }
            val parentViewModel = hiltViewModel<NewPostViewModel>(parentEntry)
            PersonCountRoute(
                modifier = modifier,
                navController = navController,
                newPostViewModel = parentViewModel
            )
        }
    }
}

sealed class NewPostRoutes(val route: String) {
    @Immutable
    object From : NewPostRoutes("From")

    @Immutable
    object To : NewPostRoutes("To")

    @Immutable
    object Direction : NewPostRoutes("Direction")

    @Immutable
    object AddLocation : NewPostRoutes("AddLocation")

    @Immutable
    object Price : NewPostRoutes("Price")

    @Immutable
    object PersonCount : NewPostRoutes("PersonCount")

    @Immutable
    object DateTime : NewPostRoutes("DateTime")
}