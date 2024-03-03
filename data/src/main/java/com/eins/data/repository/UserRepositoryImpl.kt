package com.eins.data.repository

import com.eins.data.remote.RetrofitServer
import com.eins.data.remote.data.GetUserInfoRequest
import com.eins.data.util.BaseResponseResult
import com.eins.data.util.SafeApiHandler
import com.eins.domain.entity.User
import com.eins.domain.repository.UserRepository
import com.eins.domain.util.Resource

class UserRepositoryImpl: UserRepository {
    override suspend fun GetUser(id: String, pass: String): Resource<User> =
        Resource.create {
            when(val result = SafeApiHandler.handleApi {
                RetrofitServer.userService().GetUserInfo(id, pass)
            }){
                is BaseResponseResult.Error -> Resource.Error(result.message)
                is BaseResponseResult.Success -> Resource.Success(result.data)
            }
        }.convert {
            User(
                nickName = it.nickName,
                email = it.email,
                refreshToken = it.refreshToken,
                accessToken = it.accessToken
            )
        }
}