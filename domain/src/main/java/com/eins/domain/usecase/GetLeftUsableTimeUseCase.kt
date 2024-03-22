package com.eins.domain.usecase

import com.eins.domain.entity.user.watt.value.Minute
import com.eins.domain.repository.IUserRepository
import javax.inject.Inject

class GetLeftUsableTimeUseCase @Inject constructor(
    private val iUserRepository: IUserRepository
) {
    suspend operator fun invoke(): Minute = iUserRepository.getLeftUsableTime()
}