package com.eins.data.repository

import com.eins.domain.repository.GetLeftUsableTimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetLeftUsableTimeRepositoryImpl: GetLeftUsableTimeRepository {
    override suspend fun getLeftUsableTime(): Flow<GetLeftUsableTimeRepository.Minute> = flow {
        emit(GetLeftUsableTimeRepository.Minute(10))
    }
}