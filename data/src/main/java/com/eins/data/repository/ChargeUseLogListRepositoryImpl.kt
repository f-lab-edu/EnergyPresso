package com.eins.data.repository

import com.eins.domain.entity.ChargeUseLog
import com.eins.domain.repository.ChargeUseLogListRepository

class ChargeUseLogListRepositoryImpl: ChargeUseLogListRepository {
    override suspend fun getChargeUseLogList(): List<ChargeUseLog> {
        TODO("Not yet implemented")
    }
}