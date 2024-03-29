package com.aasjunior.mediapickersuite.config.security

import android.util.Log
import com.aasjunior.mediapickersuite.config.retrofit.ApiClient
import com.aasjunior.mediapickersuite.domain.model.login.LoginRequest
import com.aasjunior.mediapickersuite.domain.model.login.LoginState

class AuthenticationService(private val securePreferences: SecurePreferences) {
    private val apiClient = ApiClient.loginService

    suspend fun authenticate(username: String, password: String): LoginState {
        return try {
            val response = apiClient.login(
                LoginRequest(username, password)
            )

            if(response.isSuccessful){
                response.body()?.token.let { token ->
                    if(token != null) {
                        securePreferences.saveToken(token)
                        LoginState.Success(token)
                    }else{
                        Log.e("AuthenticationService", "Token is null")
                        LoginState.Error("Token is null")
                    }
                }

            }else{
                val errorBody = response.errorBody()?.string()
                Log.e("AuthenticationService", errorBody ?: "Unknown error")
                LoginState.Error(errorBody ?: "Unknown error")
            }
        }catch(e: Exception){
            Log.e("AuthenticationService", e.message ?: "Unknown error")
            LoginState.Error(e.message ?: "Unknown error")
        }
    }
}