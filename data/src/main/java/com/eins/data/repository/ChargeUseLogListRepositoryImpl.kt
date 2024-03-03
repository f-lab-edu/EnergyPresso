package com.eins.data.repository

import com.eins.data.remote.RetrofitServer
import com.eins.data.util.BaseResponseResult
import com.eins.data.util.SafeApiHandler
import com.eins.domain.entity.ChargeUseLog
import com.eins.domain.repository.ChargeUseLogListRepository
import com.eins.domain.util.Resource

class ChargeUseLogListRepositoryImpl: ChargeUseLogListRepository {
    override suspend fun getChargeUseLogList(): Resource<List<ChargeUseLog>>
    = Resource.create {
        when(val result = SafeApiHandler.handleApi {
            RetrofitServer.userService().GetCargeUseLogList()
        }){
            is BaseResponseResult.Error -> Resource.Error(message = result.message)
            is BaseResponseResult.Success -> Resource.Success(data = result.data)
        }
    }
}