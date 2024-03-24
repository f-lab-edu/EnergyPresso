package com.eins.domain.entity

import com.eins.domain.entity.cafe.find.value.CafeName
import com.eins.domain.entity.log.value.ChargeDate
import com.eins.domain.entity.log.value.ChargePoint
import com.eins.domain.entity.log.value.UseEndDate
import com.eins.domain.entity.log.value.UseStartDate
import com.eins.domain.entity.log.value.UseWatt
import java.time.LocalDateTime


sealed class ChargeUseLog{
    data class ChargeLog(
        val chargePoint: ChargePoint,
        val chargeDate: ChargeDate
    ): ChargeUseLog()

    data class UseLog(
        val cafeName: CafeName,
        val useWatt: UseWatt,
        val useStartDate: UseStartDate,
        val useEndDate: UseEndDate
    ): ChargeUseLog()
}