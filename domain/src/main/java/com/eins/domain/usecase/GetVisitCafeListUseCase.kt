package com.eins.domain.usecase

import com.eins.domain.entity.VisitedCafe
import com.eins.domain.repository.ICafeRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetVisitCafeListUseCase @Inject constructor(
    private val iCafeRepository: ICafeRepository
) {
    suspend operator fun invoke() = flow<List<VisitedCafe>> {
        emit(iCafeRepository.getVisitCafeList())
    }
}