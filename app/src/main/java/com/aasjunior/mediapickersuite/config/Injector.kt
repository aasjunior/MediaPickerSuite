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
    private lateinit var languageManager: LanguageManager
    private lateinit var retrofitClient: Retrofit

    fun initialize(context: Context){
        securePreferences = SecurePreferences(context)
        authService = AuthenticationService(securePreferences)
        languageManager = LanguageManager(context)
        retrofitClient = RetrofitClient.retrofit
    }

    fun provideSecurePreferences(): SecurePreferences = securePreferences
    fun provideAuthService(): AuthenticationService = authService
    fun provideLanguageManager(): LanguageManager = languageManager
    fun provideRetrofitClient(): Retrofit = retrofitClient
}