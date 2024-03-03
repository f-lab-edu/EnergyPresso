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
){
    override fun toString(): String {
        return "$hour 시간 $min 분"
    }
}