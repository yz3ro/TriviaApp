package com.yz3ro.triviaapp.presentation

sealed class Screen (val route:String) {
    object OnBoardingScreen : Screen("onboarding_screen")
    object FeedScreen : Screen("feed_screen")
    object AuthScreen : Screen("auth_screen")
    object UserDataScreen : Screen("user_data_screen")
}