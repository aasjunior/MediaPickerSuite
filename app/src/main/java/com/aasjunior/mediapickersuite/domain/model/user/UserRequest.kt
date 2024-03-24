package com.aasjunior.mediapickersuite.domain.model.user

data class UserRequest(
    private val name: String,
    private val username: String,
    private val email: String,
    private val password: String,
    private val role: UserRole
)