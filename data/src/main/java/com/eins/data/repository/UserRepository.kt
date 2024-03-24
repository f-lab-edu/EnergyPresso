package com.eins.data.repository

import com.eins.domain.entity.ChargeUseLog
import com.eins.domain.entity.cafe.find.value.CafeName
import com.eins.domain.entity.log.value.ChargeDate
import com.eins.domain.entity.log.value.ChargePoint
import com.eins.domain.entity.log.value.UseEndDate
import com.eins.domain.entity.log.value.UseStartDate
import com.eins.domain.entity.log.value.UseWatt
import com.eins.domain.entity.user.watt.value.Minute
import com.eins.domain.entity.user.watt.value.Watt
import com.eins.domain.repository.IUserRepository
import java.time.LocalDate
import java.time.LocalDateTime

class UserRepository: IUserRepository {
    override suspend fun getUsableWatt(): Watt = Watt(1000)
    override suspend fun getLeftUsableTime(): Minute = Minute(120)
    override suspend fun getChargeUseLogList(): List<ChargeUseLog> = arrayListOf<ChargeUseLog>().apply {
        for (i in 0 .. 10){
            if(i % 2 == 0){
                this.add(ChargeUseLog.ChargeLog(
                        chargePoint = ChargePoint(100 * i),
                        chargeDate = ChargeDate(LocalDateTime.now())
                    )
                )
            }else{
                this.add(ChargeUseLog.UseLog(
                    cafeName = CafeName("POLKI"),
                    useWatt = UseWatt(100 * i),
                    useStartDate = UseStartDate(LocalDateTime.now()),
                    useEndDate = UseEndDate(LocalDateTime.now())
                ))
            }
        }
    }

    override suspend fun getUseLogList(): List<ChargeUseLog.UseLog> =
        arrayListOf<ChargeUseLog.UseLog>().apply {
            for (i in 0 .. 10){
                this.add(ChargeUseLog.UseLog(
                    cafeName = CafeName("POLKI"),
                    useWatt = UseWatt(100 * i),
                    useStartDate = UseStartDate(LocalDateTime.now()),
                    useEndDate = UseEndDate(LocalDateTime.now())
                ))
            }
        }

    override suspend fun getChargeLogList(): List<ChargeUseLog.ChargeLog> =
        arrayListOf<ChargeUseLog.ChargeLog>().apply {
            for (i in 0 .. 10){
                this.add(ChargeUseLog.ChargeLog(
                    chargePoint = ChargePoint(100 * i),
                    chargeDate = ChargeDate(LocalDateTime.now())
                ))
            }
        }
}