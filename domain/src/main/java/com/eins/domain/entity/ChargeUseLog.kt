package com.eins.domain.entity

import java.time.LocalDateTime


sealed class ChargeUseLog{
    data class ChargeLog(
        val chargeAmount: Int,
        val chargeDataTime: LocalDateTime
    )

    data class UseLog(
        val useAmount: Int,
        val startUseTime: LocalDateTime,
        val endUseTime: LocalDateTime
    )
}