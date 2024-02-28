package com.eins.domain.entity

data class VisitedCafe(
    val cafeName: String,
    val address: String,
    val useTime: UseTime,
    val useWatt: Int
)

data class UseTime(
    val hour: Int,
    val min: Int
)