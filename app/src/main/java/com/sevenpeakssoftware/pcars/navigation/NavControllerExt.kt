package com.sevenpeakssoftware.pcars.navigation // ktlint-disable filename

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sevenpeakssoftware.article_ui.ArticleScreen
import com.sevenpeakssoftware.core.navigation.Screen
import com.sevenpeakssoftware.core.util.UiEvent
import com.sevenpeakssoftware.login_ui.LoginScreen
import com.sevenpeakssoftware.pcars.SplashScreen

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController)
        }
        composable(route = Screen.Car.route) {
            ArticleScreen()
        }
        composable(route = Screen.Login.route) {
            LoginScreen(navController)
        }
    }
}
