package com.aasjunior.mediapickersuite.config

import android.content.Context
import com.aasjunior.mediapickersuite.config.preferences.LanguageManager
import com.aasjunior.mediapickersuite.config.retrofit.RetrofitClient
import com.aasjunior.mediapickersuite.config.security.AuthenticationService
import com.aasjunior.mediapickersuite.config.security.SecurePreferences
import retrofit2.Retrofit

object Injector {
    private lateinit var securePreferences: SecurePreferences
    private lateinit var authService: AuthenticationService
    private lateinit var retrofitClient: Retrofit

    fun initialize(context: Context){
        securePreferences = SecurePreferences(context)
        authService = AuthenticationService(securePreferences)
        retrofitClient = RetrofitClient.retrofit
    }

    fun provideSecurePreferences(): SecurePreferences = securePreferences
    fun provideAuthService(): AuthenticationService = authService
    fun provideRetrofitClient(): Retrofit = retrofitClient
}