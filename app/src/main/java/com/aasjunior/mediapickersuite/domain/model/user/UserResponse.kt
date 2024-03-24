package com.aasjunior.mediapickersuite.domain.model.user

import java.time.LocalDateTime

data class UserResponse(
    private val id: String,
    private val name: String,
    private val username: String,
    private val email: String,
    private val role: UserRole,
    private val registrationDate: LocalDateTime
)