package com.eins.domain.repository

import com.eins.domain.entity.User

interface IAuthRepository {
    suspend fun login(id: String, pass: String): User
}