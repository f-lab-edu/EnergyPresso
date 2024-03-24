package com.eins.domain.entity.log.value

@JvmInline
value class UseWatt(private val watt: Int) {
    fun get() = watt
}