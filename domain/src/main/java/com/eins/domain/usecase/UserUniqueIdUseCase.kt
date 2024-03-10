package com.eins.domain.usecase

import com.eins.domain.repository.UserUniqueIdRepository

class UserUniqueIdUseCase constructor(
    private val userUniqueIdRepository: UserUniqueIdRepository
) {
    fun getUserUID(): String = userUniqueIdRepository.getUserUniqueId()
    fun saveUID(uid: String) = userUniqueIdRepository.saveUserUniqueId(uid)
}