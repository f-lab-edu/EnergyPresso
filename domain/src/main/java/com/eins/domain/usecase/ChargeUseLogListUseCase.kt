package com.eins.domain.usecase

import com.eins.domain.entity.ChargeUseLog
import com.eins.domain.repository.IUserRepository
import javax.inject.Inject

class ChargeUseLogListUseCase @Inject constructor(
    private val iUserRepository: IUserRepository
) {
    suspend operator fun invoke(): List<ChargeUseLog> = iUserRepository.getChargeUseLogList()
}