package com.ivtogi.workouttimer.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ivtogi.workouttimer.ui.screens.amrap.AmrapScreen
import com.ivtogi.workouttimer.ui.screens.emom.EmomScreen
import com.ivtogi.workouttimer.ui.screens.fortime.composable.ForTimeScreen
import com.ivtogi.workouttimer.ui.screens.home.HomeScreen
import com.ivtogi.workouttimer.ui.screens.timer.TimerScreen

@Composable
fun Navigation(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Home) {
        composable<Home> {
            HomeScreen(
                onForTimeClick = { navHostController.navigate(ForTime) },
                onEmomClick = { navHostController.navigate(Emom) },
                onAmrapClick = { navHostController.navigate(Amrap) }
            )
        }
        composable<ForTime> {
            ForTimeScreen(
                onBackClick = { navHostController.popBackStack() },
                navigateToTimer = { navHostController.navigate(Timer) }
            )
        }

        composable<Emom> {
            EmomScreen { navHostController.popBackStack() }
        }

        composable<Amrap> {
            AmrapScreen { navHostController.popBackStack() }
        }

        composable<Timer> {
            TimerScreen { navHostController.popBackStack() }
        }
    }
}