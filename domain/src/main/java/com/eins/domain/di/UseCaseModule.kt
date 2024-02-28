package com.eins.domain.di

import com.eins.domain.repository.ChargeUseLogListRepository
import com.eins.domain.repository.GetCafeCurrentInfoRepository
import com.eins.domain.repository.UserRepository
import com.eins.domain.repository.VisitCafeListRepository
import com.eins.domain.usecase.CafeCurrentInfoUseCase
import com.eins.domain.usecase.ChargeUseLogListUseCase
import com.eins.domain.usecase.UserUseCase
import com.eins.domain.usecase.VisitCafeListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun userUseCase(userRepository: UserRepository) = UserUseCase(userRepository)

    @Provides
    fun cafeCurrentInfoUseCase(cafeCurrentInfoRepository: GetCafeCurrentInfoRepository): CafeCurrentInfoUseCase = CafeCurrentInfoUseCase(cafeCurrentInfoRepository)

    @Provides
    fun chargeUseLogListUseCase(chargeUseLogListRepository: ChargeUseLogListRepository): ChargeUseLogListUseCase = ChargeUseLogListUseCase(chargeUseLogListRepository)

    @Provides
    fun visitCafeListUseCase(visitCafeListRepository: VisitCafeListRepository): VisitCafeListUseCase = VisitCafeListUseCase(visitCafeListRepository)
}