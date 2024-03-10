package com.eins.domain.repository

interface UserUniqueIdRepository {
    fun getUserUniqueId(): String
    fun saveUserUniqueId(uniqueId: String): Boolean
}