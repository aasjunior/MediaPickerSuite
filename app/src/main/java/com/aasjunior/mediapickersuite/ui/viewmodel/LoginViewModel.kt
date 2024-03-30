package com.aasjunior.mediapickersuite.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aasjunior.mediapickersuite.config.security.AuthenticationService
import com.aasjunior.mediapickersuite.config.security.SecurePreferences
import com.aasjunior.mediapickersuite.domain.model.login.LoginState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authService: AuthenticationService,
    private val securePreferences: SecurePreferences
): ViewModel(){
    private val _loginState = MutableStateFlow<LoginState?>(null)
    val loginState: StateFlow<LoginState?> get() = _loginState

    fun login(email: String, password: String){
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            val result = authService.authenticate(email, password)
            _loginState.value = result
        }
    }

    fun isLoggedIn(): Flow<Boolean> {
        return securePreferences.token.map { it != null }
    }
}