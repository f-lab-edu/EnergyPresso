package com.eins.domain.repository

import com.eins.domain.entity.CafeCurrentInfo

interface GetCafeCurrentInfoRepository {
    fun getCafeCurrentInfo(id: Int): CafeCurrentInfo
}