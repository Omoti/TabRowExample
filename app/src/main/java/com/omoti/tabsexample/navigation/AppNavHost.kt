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
import com.omoti.tabsexample.screens.StickyScreen
import com.omoti.tabsexample.screens.TabRowWithPagerScreen

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
                onStickyClick = { initialTabIndex ->
                    navController.navigate(route = "sticky/${initialTabIndex}")
                },
                onCustomClick = { initialTabIndex ->
                    navController.navigate(route = "custom/${initialTabIndex}")
                },
                onPagerClick = { initialTabIndex ->
                    navController.navigate(route = "pager/${initialTabIndex}")
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
            route = "sticky/{initialTabIndex}",
            arguments = listOf(navArgument("initialTabIndex") { type = NavType.IntType }),
        ) { backStackEntry ->
            StickyScreen(
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
        composable(
            route = "pager/{initialTabIndex}",
            arguments = listOf(navArgument("initialTabIndex") { type = NavType.IntType }),
        ) { backStackEntry ->
            TabRowWithPagerScreen(
                onBack = { navController.popBackStack() },
                initialTabIndex = backStackEntry.arguments?.getInt("initialTabIndex") ?: 0,
            )
        }
    }
}
