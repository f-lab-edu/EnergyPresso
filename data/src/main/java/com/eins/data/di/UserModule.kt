package com.eins.data.di

import com.eins.data.repository.UserRepositoryImpl
import com.eins.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UserModule {
    @Provides
    fun UserRepository(): UserRepository = UserRepositoryImpl()
}