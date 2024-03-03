package com.eins.data.repository

import com.eins.data.remote.RetrofitServer
import com.eins.data.util.BaseResponseResult
import com.eins.data.util.SafeApiHandler
import com.eins.domain.entity.VisitedCafe
import com.eins.domain.repository.VisitCafeListRepository
import com.eins.domain.util.Resource

class VisitCafeListRepositoryImpl: VisitCafeListRepository {
    override suspend fun getVisitCageList(): Resource<List<VisitedCafe>>
    = Resource.create {
        when(val result = SafeApiHandler.handleApi {
            RetrofitServer.userService().GetVisiteCafeList()
        }){
            is BaseResponseResult.Error -> Resource.Error(message = result.message)
            is BaseResponseResult.Success -> Resource.Success(data = result.data)
        }
    }
}