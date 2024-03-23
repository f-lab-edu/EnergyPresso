package com.eins.data.remote.service

import com.eins.data.remote.BaseResponse
import com.eins.data.remote.data.GetUserInfoRequest
import com.eins.data.remote.data.GetUserInfoResponse
import com.eins.domain.entity.ChargeUseLog
import com.eins.domain.entity.VisitedCafe
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("/cafe/visit/list")
    suspend fun GetVisiteCafeList(): Response<BaseResponse<List<VisitedCafe>>>

    @GET("/chargeuge/list")
    suspend fun GetCargeUseLogList(): Response<BaseResponse<List<ChargeUseLog>>>

    @GET("/watt/usable")
    suspend fun GetUsableWattAmount(): Response<BaseResponse<Int>>
}