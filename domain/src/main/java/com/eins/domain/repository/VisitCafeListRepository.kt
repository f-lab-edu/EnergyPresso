package com.eins.domain.repository

import com.eins.domain.entity.VisitedCafe
import com.eins.domain.util.Resource

interface VisitCafeListRepository {
    suspend fun getVisitCageList(): Resource<List<VisitedCafe>>
}