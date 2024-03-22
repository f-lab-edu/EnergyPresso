package com.eins.domain.usecase

import com.eins.domain.entity.CafeCurrentInfo
import com.eins.domain.repository.ICafeRepository
import javax.inject.Inject

class CafeCurrentInfoUseCase @Inject constructor(
    private val iCafeRepository: ICafeRepository
){
    suspend fun getCafeCurrentInfoUseCase(id: Int): CafeCurrentInfo = iCafeRepository.getCafeCurrentInfo(id)
}