package com.eins.domain.usecase

import com.eins.domain.entity.User
import com.eins.domain.repository.IAuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor (
    private val iAuthRepository: IAuthRepository
) {
    suspend operator fun invoke(id: String, pass: String): User = iAuthRepository.login(id, pass)
}