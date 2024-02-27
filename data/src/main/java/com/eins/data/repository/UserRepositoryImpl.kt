package com.eins.data.repository

import com.eins.domain.entity.User
import com.eins.domain.repository.UserRepository

class UserRepositoryImpl: UserRepository {
    override suspend fun GetUser(id: String, pass: String): User {
        return User(
            nickName = "kdb",
            email = "kdb@kdb.com"
        )
    }
}