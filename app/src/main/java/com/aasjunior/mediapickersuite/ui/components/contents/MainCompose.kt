package com.aasjunior.mediapickersuite.ui.components.contents

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.aasjunior.mediapickersuite.domain.model.login.LoginState
import com.aasjunior.mediapickersuite.ui.components.contents.appdrawer.AppDrawerContent
import com.aasjunior.mediapickersuite.ui.components.contents.appdrawer.DrawerParams
import com.aasjunior.mediapickersuite.ui.navigation.introGraph
import com.aasjunior.mediapickersuite.ui.navigation.mainGraph
import com.aasjunior.mediapickersuite.ui.navigation.routes.MainNavOptions
import com.aasjunior.mediapickersuite.ui.navigation.routes.NavRoutes
import com.aasjunior.mediapickersuite.ui.theme.MediaPickerSuiteTheme
import com.aasjunior.mediapickersuite.ui.viewmodel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainCompose(
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )
){
    val loginViewModel: LoginViewModel = viewModel()

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
                val isLoggedIn = loginViewModel.loginState.collectAsState()
                NavHost(
                    navController,
                    startDestination =
                        if (isLoggedIn.value is LoginState.Success) NavRoutes.MainRoute.name
                        else NavRoutes.IntroRoute.name
                ){
                    introGraph(navController, loginViewModel)
                    mainGraph(drawerState)
                }
            }
        }
    }
}