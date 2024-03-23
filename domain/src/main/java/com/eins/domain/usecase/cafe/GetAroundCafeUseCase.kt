package com.eins.domain.usecase.cafe

import com.eins.domain.entity.cafe.find.AroundCafeData
import com.eins.domain.repository.ICafeRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAroundCafeUseCase @Inject constructor(
    private val iCafeRepository: ICafeRepository
) {
    suspend operator fun invoke() = flow<List<AroundCafeData>> {
        emit(iCafeRepository.getAroundCafeList())
    }
}