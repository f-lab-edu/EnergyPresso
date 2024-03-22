package com.eins.domain.repository

import com.eins.domain.entity.ChargeUseLog
import com.eins.domain.entity.User
import com.eins.domain.entity.user.watt.value.Minute
import com.eins.domain.entity.user.watt.value.Watt
import com.eins.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    suspend fun getUsableWatt(): Watt
    suspend fun getLeftUsableTime(): Minute
    suspend fun getUseLogList(): List<ChargeUseLog>
}