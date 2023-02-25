package com.omoti.tabsexample.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.omoti.tabsexample.screens.CollapseScreen
import com.omoti.tabsexample.screens.CustomTabRowScreen
import com.omoti.tabsexample.screens.FixedTabRowScreen
import com.omoti.tabsexample.screens.MainScreen
import com.omoti.tabsexample.screens.ScrollableTabRowScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "main") {
        composable(route = "main") {
            MainScreen(
                onFixedClick = { initialTabIndex ->
                    navController.navigate(route = "fixed/${initialTabIndex}")
                },
                onScrollableClick = { initialTabIndex ->
                    navController.navigate(route = "scrollable/${initialTabIndex}")
                },
                onCollapseClick = { initialTabIndex ->
                    navController.navigate(route = "collapse/${initialTabIndex}")
                },
                onCustomClick = { initialTabIndex ->
                    navController.navigate(route = "custom/${initialTabIndex}")
                },
            )
        }
        composable(
            route = "fixed/{initialTabIndex}",
            arguments = listOf(navArgument("initialTabIndex") { type = NavType.IntType }),
        ) { backStackEntry ->
            FixedTabRowScreen(
                onBack = { navController.popBackStack() },
                initialTabIndex = backStackEntry.arguments?.getInt("initialTabIndex") ?: 0,
            )
        }
        composable(
            route = "scrollable/{initialTabIndex}",
            arguments = listOf(navArgument("initialTabIndex") { type = NavType.IntType }),
        ) { backStackEntry ->
            ScrollableTabRowScreen(
                onBack = { navController.popBackStack() },
                initialTabIndex = backStackEntry.arguments?.getInt("initialTabIndex") ?: 0,
            )
        }
        composable(
            route = "collapse/{initialTabIndex}",
            arguments = listOf(navArgument("initialTabIndex") { type = NavType.IntType }),
        ) { backStackEntry ->
            CollapseScreen(
                onBack = { navController.popBackStack() },
                initialTabIndex = backStackEntry.arguments?.getInt("initialTabIndex") ?: 0,
            )
        }
        composable(
            route = "custom/{initialTabIndex}",
            arguments = listOf(navArgument("initialTabIndex") { type = NavType.IntType }),
        ) { backStackEntry ->
            CustomTabRowScreen(
                onBack = { navController.popBackStack() },
                initialTabIndex = backStackEntry.arguments?.getInt("initialTabIndex") ?: 0,
            )
        }
    }
}
