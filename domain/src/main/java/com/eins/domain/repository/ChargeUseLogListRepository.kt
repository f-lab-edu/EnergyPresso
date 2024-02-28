package com.eins.domain.repository

import com.eins.domain.entity.ChargeUseLog

interface ChargeUseLogListRepository {
    suspend fun getChargeUseLogList(): List<ChargeUseLog>
}