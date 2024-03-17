package com.eins.domain.repository

import kotlinx.coroutines.flow.Flow

interface GetLeftUsableTimeRepository {
    @JvmInline
    value class Minute(private val minute: Int){
        fun get() = minute
    }

    suspend fun getLeftUsableTime(): Flow<Minute>
}