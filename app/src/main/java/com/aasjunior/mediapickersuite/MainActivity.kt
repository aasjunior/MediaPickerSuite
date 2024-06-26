package com.aasjunior.mediapickersuite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.aasjunior.mediapickersuite.config.Injector
import com.aasjunior.mediapickersuite.ui.components.contents.LocalizedContent
import com.aasjunior.mediapickersuite.ui.components.contents.MainCompose
import com.aasjunior.mediapickersuite.ui.theme.MediaPickerSuiteTheme
import com.aasjunior.mediapickersuite.ui.viewmodel.GenericViewModelFactory
import com.aasjunior.mediapickersuite.ui.viewmodel.LoginViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val authService = Injector.provideAuthService()
        val securePreferences = Injector.provideSecurePreferences()
        val factory = GenericViewModelFactory {
            LoginViewModel(authService, securePreferences)
        }
        val loginViewModel = ViewModelProvider(this, factory)
            .get(LoginViewModel::class.java)

        setContent {
            MediaPickerSuiteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LocalizedContent {
                        MainCompose(loginViewModel = loginViewModel)
                    }
                }
            }
        }
    }
}