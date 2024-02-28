package com.eins.data.repository

import com.eins.domain.entity.VisitedCafe
import com.eins.domain.repository.VisitCafeListRepository

class VisitCafeListRepositoryImpl: VisitCafeListRepository {
    override suspend fun getVisitCageList(): List<VisitedCafe> {
        TODO("Not yet implemented")
    }
}