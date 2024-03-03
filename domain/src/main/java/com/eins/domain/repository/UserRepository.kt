package com.eins.domain.repository

import com.eins.domain.entity.User
import com.eins.domain.util.Resource

interface UserRepository {
    suspend fun GetUser(id: String, pass: String): Resource<User>
}