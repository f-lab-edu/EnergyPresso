package com.eins.data.repository

import com.eins.domain.entity.ChargeUseLog
import com.eins.domain.entity.user.watt.value.Minute
import com.eins.domain.entity.user.watt.value.Watt
import com.eins.domain.repository.IUserRepository
import java.time.LocalDate
import java.time.LocalDateTime

class UserRepository: IUserRepository {
    override suspend fun getUsableWatt(): Watt = Watt(1000)
    override suspend fun getLeftUsableTime(): Minute = Minute(120)
    override suspend fun getUseLogList(): List<ChargeUseLog> = arrayListOf<ChargeUseLog>().apply {
        for (i in 0 .. 10){
            if(i % 2 == 0){
                this.add(ChargeUseLog.ChargeLog(chargeAmount = i * 100, chargeDataTime = LocalDateTime.now()))
            }else{
                this.add(ChargeUseLog.UseLog(useAmount = i * 50, startUseTime = LocalDateTime.now(), endUseTime = LocalDateTime.now()))
            }
        }
    }
}