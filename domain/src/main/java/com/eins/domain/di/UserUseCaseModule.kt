package com.eins.domain.di

import com.eins.domain.repository.UserRepository
import com.eins.domain.usecase.UserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserUseCaseModule {
    @Provides
    fun ProvideUserUseCase(userRepository: UserRepository) = UserUseCase(userRepository)
}