package com.eins.domain.entity.log.value

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@JvmInline
value class UseStartDate(private val date: LocalDateTime) {
    fun get() = date
    fun getString() = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
}