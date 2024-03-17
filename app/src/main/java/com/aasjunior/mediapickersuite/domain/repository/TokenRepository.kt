package com.aasjunior.mediapickersuite.domain.repository

import kotlinx.coroutines.flow.Flow

interface TokenRepository {
    suspend fun saveToken(token: String)
    val token: Flow<String?>
}