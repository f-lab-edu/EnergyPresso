package com.eins.domain.usecase

import com.eins.domain.entity.User
import com.eins.domain.repository.UserRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun getUser(id: String, pass: String): User = userRepository.GetUser(id, pass)
}