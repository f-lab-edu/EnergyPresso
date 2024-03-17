package com.eins.domain.usecase

import com.eins.domain.repository.GetLeftUsableTimeRepository
import kotlinx.coroutines.flow.Flow

class GetLeftUsableTimeUseCase(
    private val getLeftUsableTimeRepository: GetLeftUsableTimeRepository
) {
    suspend fun getLeftUsableTime(): Flow<GetLeftUsableTimeRepository.Minute> = getLeftUsableTimeRepository.getLeftUsableTime()
}