package com.eins.domain.usecase

import com.eins.domain.entity.CafeCurrentInfo
import com.eins.domain.repository.GetCafeCurrentInfoRepository
import com.eins.domain.util.Resource

class CafeCurrentInfoUseCase constructor(
    private val getCafeCurrentInfoRepository: GetCafeCurrentInfoRepository
){
    suspend fun getCafeCurrentInfoUseCase(id: Int): Resource<CafeCurrentInfo> = getCafeCurrentInfoRepository.getCafeCurrentInfo(id)
}