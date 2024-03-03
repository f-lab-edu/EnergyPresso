package com.eins.data.repository

import com.eins.data.remote.RetrofitServer
import com.eins.data.util.SafeApiHandler
import com.eins.domain.entity.CafeCurrentInfo
import com.eins.domain.repository.GetCafeCurrentInfoRepository
import com.eins.domain.util.Resource

class GetCafeCurrentInfoRepositoryImpl: GetCafeCurrentInfoRepository {
    override fun getCafeCurrentInfo(id: Int): Resource<CafeCurrentInfo>
    = TODO()
}