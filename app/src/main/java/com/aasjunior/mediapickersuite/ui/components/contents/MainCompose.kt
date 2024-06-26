package com.aasjunior.mediapickersuite.ui.components.contents

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.aasjunior.mediapickersuite.ui.components.contents.appdrawer.AppDrawerContent
import com.aasjunior.mediapickersuite.ui.components.contents.appdrawer.DrawerParams
import com.aasjunior.mediapickersuite.ui.navigation.introGraph
import com.aasjunior.mediapickersuite.ui.navigation.mainGraph
import com.aasjunior.mediapickersuite.ui.navigation.routes.MainNavOptions
import com.aasjunior.mediapickersuite.ui.navigation.routes.NavRoutes
import com.aasjunior.mediapickersuite.ui.screens.SplashScreen
import com.aasjunior.mediapickersuite.ui.theme.MediaPickerSuiteTheme
import com.aasjunior.mediapickersuite.ui.viewmodel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainCompose(
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    ),
    loginViewModel: LoginViewModel
){
    var isLoggedIn = loginViewModel.isLoggedIn()
        .collectAsState(initial = null)

    MediaPickerSuiteTheme {
        Surface {
            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    AppDrawerContent(
                        drawerState = drawerState,
                        menuItems = DrawerParams.drawerButtons,
                        defaultPick = MainNavOptions.HelloScreen
                    ){ onUserPickedOption ->
                        when(onUserPickedOption){
                            MainNavOptions.HelloScreen -> {
                                navController.navigate(onUserPickedOption.name){
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }
                            MainNavOptions.RegisterScreen -> {
                                navController.navigate(onUserPickedOption.name){
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }
                        }
                    }
                }
            ) {
                when(isLoggedIn.value){
                    null -> SplashScreen()

                    true -> NavHost(
                        navController,
                        startDestination = NavRoutes.MainRoute.name
                    ){
                        introGraph(navController, loginViewModel)
                        mainGraph(drawerState, loginViewModel)
                    }

                    false -> NavHost(
                        navController,
                        startDestination = NavRoutes.IntroRoute.name
                    ){
                        introGraph(navController, loginViewModel)
                        mainGraph(drawerState, loginViewModel)
                    }
                }
            }
        }
    }
}