package com.eins.domain.entity.log

import com.eins.domain.entity.cafe.find.value.CafeName
import com.eins.domain.entity.log.value.UseEndDate
import com.eins.domain.entity.log.value.UseStartDate
import com.eins.domain.entity.log.value.UseWatt

data class UseLog(
    val cafeName: CafeName,
    val useWatt: UseWatt,
    val useStartDate: UseStartDate,
    val useEndDate: UseEndDate
)
