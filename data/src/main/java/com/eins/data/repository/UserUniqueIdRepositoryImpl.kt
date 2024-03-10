package com.eins.data.repository

import com.eins.data.datasource.InAppDataSharedPref
import com.eins.domain.repository.UserUniqueIdRepository

class UserUniqueIdRepositoryImpl: UserUniqueIdRepository {
    override fun getUserUniqueId(): String = InAppDataSharedPref.getUniqueUserId() ?: ""
    override fun saveUserUniqueId(uniqueId: String): Boolean = InAppDataSharedPref.saveUniqueUserId(uniqueId)
}