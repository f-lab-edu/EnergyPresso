package com.eins.domain.repository

import com.eins.domain.entity.User

interface UserRepository {
    suspend fun GetUser(id: String, pass: String): User
}