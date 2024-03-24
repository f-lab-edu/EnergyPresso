package com.eins.domain.repository

import com.eins.domain.entity.ChargeUseLog
import com.eins.domain.entity.user.watt.value.Minute
import com.eins.domain.entity.user.watt.value.Watt

interface IUserRepository {
    suspend fun getUsableWatt(): Watt
    suspend fun getLeftUsableTime(): Minute
    suspend fun getChargeUseLogList(): List<ChargeUseLog>

    suspend fun getUseLogList(): List<ChargeUseLog.UseLog>
    suspend fun getChargeLogList(): List<ChargeUseLog.ChargeLog>
}