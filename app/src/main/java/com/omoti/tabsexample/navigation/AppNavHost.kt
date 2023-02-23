package com.omoti.tabsexample.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.omoti.tabsexample.screens.FixedTabRowScreen
import com.omoti.tabsexample.screens.MainScreen
import com.omoti.tabsexample.screens.ScrollableTabRowScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "main") {
        composable(route = "main") {
            MainScreen(
                onFixedClick = { navController.navigate(route = "fixed") },
                onScrollableClick = { navController.navigate(route = "scrollable") }
            )
        }
        composable(route = "fixed") {
            FixedTabRowScreen(onBack = { navController.popBackStack() })
        }
        composable(route = "scrollable") {
            ScrollableTabRowScreen(onBack = { navController.popBackStack() })
        }
    }
}
