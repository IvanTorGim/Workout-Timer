package com.itortosagimeno.workouttimer.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.itortosagimeno.workouttimer.ui.screens.home.HomeScreen
import com.itortosagimeno.workouttimer.ui.screens.select_timer.SelectTimerScreen
import com.itortosagimeno.workouttimer.ui.screens.timer.TimerScreen

@Composable
fun Navigation(navHostController: NavHostController = rememberNavController()) {
    NavHost(navController = navHostController, startDestination = HomeRoute) {
        composable<HomeRoute> {
            HomeScreen { navHostController.navigate(SelectorRoute(it)) }
        }
        composable<SelectorRoute> {
            SelectTimerScreen(
                onBackClick = { navHostController.popBackStack() },
                navigateToTimer = { navHostController.navigate(TimerRoute(it)) }
            )
        }

        composable<TimerRoute> {
            TimerScreen { navHostController.popBackStack() }
        }
    }
}