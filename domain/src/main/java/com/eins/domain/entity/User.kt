package com.eins.domain.entity

data class User(
    val nickName: String,
    val email: String,
    val refreshToken: String,
    val accessToken: String
)
