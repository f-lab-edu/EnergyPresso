package com.eins.domain.repository

import com.eins.domain.util.Resource

interface GetUsableWattRepository {
    suspend fun getUsableWatt(): Resource<Int>
}