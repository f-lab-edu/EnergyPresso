package com.eins.data.remote.data

data class GetUserInfoResponse(
    val nickName: String,
    val email: String,
    val refreshToken: String,
    val accessToken: String
)
