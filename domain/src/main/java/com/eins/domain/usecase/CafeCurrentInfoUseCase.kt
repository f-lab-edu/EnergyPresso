package com.eins.domain.usecase

import com.eins.domain.entity.CafeCurrentInfo
import com.eins.domain.repository.GetCafeCurrentInfoRepository

class CafeCurrentInfoUseCase constructor(
    private val getCafeCurrentInfoRepository: GetCafeCurrentInfoRepository
){
    suspend fun getCafeCurrentInfoUseCase(id: Int): CafeCurrentInfo = getCafeCurrentInfoRepository.getCafeCurrentInfo(id)
}