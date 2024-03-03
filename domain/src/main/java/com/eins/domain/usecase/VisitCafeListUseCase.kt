package com.eins.domain.usecase

import com.eins.domain.entity.VisitedCafe
import com.eins.domain.repository.VisitCafeListRepository
import com.eins.domain.util.Resource

class VisitCafeListUseCase constructor(
    private val visitCafeListRepository: VisitCafeListRepository
) {
    suspend fun getVisitCafeList(): Resource<List<VisitedCafe>> = visitCafeListRepository.getVisitCageList()
}