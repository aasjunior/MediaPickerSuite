package com.aasjunior.mediapickersuite.ui.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.aasjunior.mediapickersuite.ui.navigation.routes.MainNavOptions
import com.aasjunior.mediapickersuite.ui.navigation.routes.NavRoutes
import com.aasjunior.mediapickersuite.ui.screens.HelloScreen
import com.aasjunior.mediapickersuite.ui.screens.RegisterScreen

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.mainGraph(drawerState: DrawerState){
    navigation(
        startDestination = MainNavOptions.HelloScreen.name,
        route = NavRoutes.MainRoute.name
    ){
        composable(MainNavOptions.HelloScreen.name){
            HelloScreen(drawerState)
        }
        composable(MainNavOptions.RegisterScreen.name){
            RegisterScreen(drawerState)
        }
    }
}