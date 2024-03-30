package com.aasjunior.mediapickersuite.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aasjunior.mediapickersuite.domain.model.login.LoginState
import com.aasjunior.mediapickersuite.ui.components.form.inputs.EmailTextField
import com.aasjunior.mediapickersuite.ui.components.form.inputs.PasswordTextField
import com.aasjunior.mediapickersuite.ui.viewmodel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    val loginState by loginViewModel.loginState.collectAsState()

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            EmailTextField(
                email = email,
                onValueChange = { newEmail -> email = newEmail },
                roundedCornerShape = true
            )
            PasswordTextField(
                password = password,
                onValueChange = { newPassword -> password = newPassword }
            )
            Button(onClick = { loginViewModel.login(email, password) }) {
                Text(text = "Login")
            }

            when(val state = loginState){
                is LoginState.Loading -> { Text(text = "Validando") }
                is LoginState.Success -> { Text(text = "Logado") }
                is LoginState.Error -> { Text(text = "Erro: ${state.error}") }
                else -> Text(text = "")
            }
        }
    }
}