package com.eins.data.repository

import com.eins.data.remote.service.UserService
import com.eins.domain.entity.User
import com.eins.domain.repository.IAuthRepository
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val userService: UserService
): IAuthRepository {
    override suspend fun login(id: String, pass: String): User =
        User(
            nickName = "kdb",
            email = "kdb@flab.com",
            refreshToken = "1234",
            accessToken = "abcd"
        )
        /*
        userService.GetUserInfo(id, pass).body()!!.data!!.let {
            User(
                nickName = it.nickName,
                email = it.email,
                refreshToken = it.refreshToken,
                accessToken = it.accessToken
            )
        }

         */
}