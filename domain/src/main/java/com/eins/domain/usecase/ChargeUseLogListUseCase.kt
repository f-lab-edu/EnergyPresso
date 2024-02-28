package com.eins.domain.usecase

import com.eins.domain.entity.ChargeUseLog
import com.eins.domain.repository.ChargeUseLogListRepository

class ChargeUseLogListUseCase constructor(
    private val chargeUseLogListRepository: ChargeUseLogListRepository
) {
    suspend fun getChargeUseLogList(): List<ChargeUseLog> = chargeUseLogListRepository.getChargeUseLogList()
}