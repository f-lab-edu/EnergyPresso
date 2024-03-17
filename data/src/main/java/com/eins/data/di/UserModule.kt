package com.eins.data.di

import com.eins.data.repository.ChargeUseLogListRepositoryImpl
import com.eins.data.repository.GetCafeCurrentInfoRepositoryImpl
import com.eins.data.repository.GetLeftUsableTimeRepositoryImpl
import com.eins.data.repository.UserUniqueIdRepositoryImpl
import com.eins.data.repository.UserRepositoryImpl
import com.eins.data.repository.VisitCafeListRepositoryImpl
import com.eins.domain.repository.ChargeUseLogListRepository
import com.eins.domain.repository.GetCafeCurrentInfoRepository
import com.eins.domain.repository.GetLeftUsableTimeRepository
import com.eins.domain.repository.UserUniqueIdRepository
import com.eins.domain.repository.UserRepository
import com.eins.domain.repository.VisitCafeListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UserModule {
    @Provides
    fun UserRepository(): UserRepository = UserRepositoryImpl()

    @Provides
    fun chargeUseLogListRepositoryImpl(): ChargeUseLogListRepository = ChargeUseLogListRepositoryImpl()

    @Provides
    fun getCafeCurrentInfoRepository(): GetCafeCurrentInfoRepository = GetCafeCurrentInfoRepositoryImpl()

    @Provides
    fun visitCafeListRepository(): VisitCafeListRepository = VisitCafeListRepositoryImpl()

    @Provides
    fun getUserUniqueIdRepository(): UserUniqueIdRepository = UserUniqueIdRepositoryImpl()

    @Provides
    fun provideGetLeftUsableTimeRepository(): GetLeftUsableTimeRepository = GetLeftUsableTimeRepositoryImpl()
}