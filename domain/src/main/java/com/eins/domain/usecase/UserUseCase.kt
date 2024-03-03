package com.eins.domain.usecase

import com.eins.domain.entity.User
import com.eins.domain.repository.UserRepository
import com.eins.domain.util.Resource
import javax.inject.Inject

class UserUseCase (
    private val userRepository: UserRepository
) {
    suspend fun getUser(id: String, pass: String): Resource<User> = userRepository.GetUser(id, pass)
}