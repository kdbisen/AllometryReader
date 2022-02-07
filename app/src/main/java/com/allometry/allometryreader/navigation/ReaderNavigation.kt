package com.allometry.allometryreader.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.allometry.allometryreader.screens.ReaderSplashScreen
import com.allometry.allometryreader.screens.home.ReaderHomeScreen
import com.allometry.allometryreader.screens.login.ReaderLoginScreen


@Composable
fun ReaderNavigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ReaderScreens.SplashScreen.name ) {

        composable(ReaderScreens.SplashScreen.name){
            ReaderSplashScreen(navController = navController)
        }

        composable(ReaderScreens.ReaderHomeScreen.name){
            ReaderHomeScreen(navController = navController)
        }

        composable(ReaderScreens.LoginScreen.name){


           ReaderLoginScreen(navController = navController)
        }
    }

}