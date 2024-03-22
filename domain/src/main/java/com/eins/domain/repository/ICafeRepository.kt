package com.eins.domain.repository

import com.eins.domain.entity.CafeCurrentInfo
import com.eins.domain.entity.VisitedCafe
import com.eins.domain.util.Resource

interface ICafeRepository {
    suspend fun getVisitCafeList(): List<VisitedCafe>
    suspend fun getCafeCurrentInfo(id: Int): CafeCurrentInfo
}