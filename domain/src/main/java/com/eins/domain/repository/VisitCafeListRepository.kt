package com.eins.domain.repository

import com.eins.domain.entity.VisitedCafe

interface VisitCafeListRepository {
    suspend fun getVisitCageList(): List<VisitedCafe>
}