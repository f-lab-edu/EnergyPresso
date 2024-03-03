package com.eins.domain.repository

import com.eins.domain.entity.CafeCurrentInfo
import com.eins.domain.util.Resource

interface GetCafeCurrentInfoRepository {
    fun getCafeCurrentInfo(id: Int): Resource<CafeCurrentInfo>
}