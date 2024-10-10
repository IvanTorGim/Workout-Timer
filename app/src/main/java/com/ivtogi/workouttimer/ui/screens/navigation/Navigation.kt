package com.ivtogi.workouttimer.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ivtogi.workouttimer.ui.screens.amrap.AmrapScreen
import com.ivtogi.workouttimer.ui.screens.emom.EmomScreen
import com.ivtogi.workouttimer.ui.screens.fortime.ForTimeScreen
import com.ivtogi.workouttimer.ui.screens.home.HomeScreen
import com.ivtogi.workouttimer.ui.screens.timer.TimerScreen

@Composable
fun Navigation(navHostController: NavHostController = rememberNavController()) {
    NavHost(navController = navHostController, startDestination = HomeRoute) {
        composable<HomeRoute> {
            HomeScreen(
                onForTimeClick = { navHostController.navigate(ForTimeRoute) },
                onEmomClick = { navHostController.navigate(EmomRoute) },
                onAmrapClick = { navHostController.navigate(AmrapRoute) }
            )
        }
        composable<ForTimeRoute> {
            ForTimeScreen(
                onBackClick = { navHostController.popBackStack() },
                navigateToTimer = { navHostController.navigate(TimerRoute(it)) }
            )
        }

        composable<EmomRoute> {
            EmomScreen { navHostController.popBackStack() }
        }

        composable<AmrapRoute> {
            AmrapScreen { navHostController.popBackStack() }
        }

        composable<TimerRoute> {
            TimerScreen { navHostController.popBackStack() }
        }
    }
}