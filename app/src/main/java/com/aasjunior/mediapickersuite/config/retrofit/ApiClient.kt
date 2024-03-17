package com.aasjunior.mediapickersuite.config.retrofit

import com.aasjunior.mediapickersuite.domain.service.LoginService

object ApiClient {
    val loginService: LoginService by lazy {
        RetrofitClient.retrofit.create(LoginService::class.java)
    }
}