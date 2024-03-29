package com.aasjunior.mediapickersuite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.aasjunior.mediapickersuite.config.Injector
import com.aasjunior.mediapickersuite.ui.screens.HelloScreen
import com.aasjunior.mediapickersuite.ui.screens.LoginScreen
import com.aasjunior.mediapickersuite.ui.screens.RegisterScreen
import com.aasjunior.mediapickersuite.ui.theme.MediaPickerSuiteTheme
import com.aasjunior.mediapickersuite.ui.viewmodel.GenericViewModelFactory
import com.aasjunior.mediapickersuite.ui.viewmodel.LoginViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val authService = Injector.provideAuthService()
        val factory = GenericViewModelFactory { LoginViewModel(authService) }
        val loginViewModel = ViewModelProvider(this, factory)
            .get(LoginViewModel::class.java)

        setContent {
            MediaPickerSuiteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // LoginScreen(loginViewModel)
                    // RegisterScreen()
                    HelloScreen()
                }
            }
        }
    }
}