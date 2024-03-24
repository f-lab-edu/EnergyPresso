package com.eins.domain.entity.log

import com.eins.domain.entity.log.value.ChargeDate
import com.eins.domain.entity.log.value.ChargePoint

data class ChargeLog(
    val chargePoint: ChargePoint,
    val chargeDate: ChargeDate
)
