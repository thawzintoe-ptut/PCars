package com.sevenpeakssoftware.core.navigation

object Route {
    // Home Section
    const val CarHomeList = "car_home_list"
    const val Splash = "splash"
    const val Login = "login"
}

sealed class Screen(val route: String) {
    object Splash : Screen(Route.Splash)
    object Car : Screen(Route.CarHomeList)
    object Login : Screen(Route.Login)
}
