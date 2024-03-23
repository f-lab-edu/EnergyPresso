package com.eins.domain.repository

import com.eins.domain.entity.CafeCurrentInfo
import com.eins.domain.entity.VisitedCafe
import com.eins.domain.entity.cafe.find.AroundCafeData
import com.eins.domain.entity.cafe.find.FrequentlyCafeData
import com.eins.domain.util.Resource

interface ICafeRepository {
    suspend fun getVisitCafeList(): List<VisitedCafe>
    suspend fun getCafeCurrentInfo(id: Int): CafeCurrentInfo
    suspend fun getAroundCafeList(): List<AroundCafeData>
    suspend fun getFrequentlyCafeList(): List<FrequentlyCafeData>
}