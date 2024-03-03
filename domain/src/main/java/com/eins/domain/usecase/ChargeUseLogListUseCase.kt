package com.eins.domain.usecase

import com.eins.domain.entity.ChargeUseLog
import com.eins.domain.repository.ChargeUseLogListRepository
import com.eins.domain.util.Resource

class ChargeUseLogListUseCase constructor(
    private val chargeUseLogListRepository: ChargeUseLogListRepository
) {
    suspend fun getChargeUseLogList(): Resource<List<ChargeUseLog>> = chargeUseLogListRepository.getChargeUseLogList()
}