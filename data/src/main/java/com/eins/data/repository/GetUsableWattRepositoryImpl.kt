package com.eins.data.repository

import com.eins.data.remote.RetrofitServer
import com.eins.data.util.BaseResponseResult
import com.eins.data.util.SafeApiHandler
import com.eins.domain.entity.User
import com.eins.domain.repository.GetUsableWattRepository
import com.eins.domain.util.Resource

class GetUsableWattRepositoryImpl: GetUsableWattRepository {
    override suspend fun getUsableWatt(): Resource<Int>
    = Resource.create {
        when(val result = SafeApiHandler.handleApi {
            RetrofitServer.userService().GetUsableWattAmount()
        }){
            is BaseResponseResult.Error -> Resource.Error(result.message)
            is BaseResponseResult.Success -> Resource.Success(result.data)
        }
    }
}