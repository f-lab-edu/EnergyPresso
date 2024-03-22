package com.eins.data.di

import com.eins.data.remote.RetrofitServer
import com.eins.data.remote.service.UserService
import com.eins.data.repository.AuthRepository
import com.eins.data.repository.CafeRepository
import com.eins.data.repository.UserRepository
import com.eins.domain.repository.IAuthRepository
import com.eins.domain.repository.ICafeRepository
import com.eins.domain.repository.IUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideIUserRepository(): IUserRepository = UserRepository()

    @Provides
    fun provideIAuthRepository(): IAuthRepository = AuthRepository(RetrofitServer.userService())

    @Provides
    fun provideICafeRepository(): ICafeRepository = CafeRepository()
}