package com.eins.domain.repository

import com.eins.domain.entity.ChargeUseLog
import com.eins.domain.util.Resource

interface ChargeUseLogListRepository {
    suspend fun getChargeUseLogList(): Resource<List<ChargeUseLog>>
}