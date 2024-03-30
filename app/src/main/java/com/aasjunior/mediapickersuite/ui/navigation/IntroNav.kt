package com.aasjunior.mediapickersuite.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.aasjunior.mediapickersuite.ui.navigation.routes.IntroNavOptions
import com.aasjunior.mediapickersuite.ui.navigation.routes.NavRoutes
import com.aasjunior.mediapickersuite.ui.screens.LoginScreen
import com.aasjunior.mediapickersuite.ui.viewmodel.LoginViewModel

fun NavGraphBuilder.introGraph(navController: NavHostController, loginViewModel: LoginViewModel){
    navigation(
        startDestination = IntroNavOptions.LoginScreen.name,
        route = NavRoutes.IntroRoute.name
    ){
        composable(IntroNavOptions.LoginScreen.name){
            LoginScreen(
                navController = navController,
                loginViewModel = loginViewModel
            )
        }
    }
}