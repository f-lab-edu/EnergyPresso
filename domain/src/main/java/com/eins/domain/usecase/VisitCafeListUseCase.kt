package com.eins.domain.usecase

import com.eins.domain.entity.VisitedCafe
import com.eins.domain.repository.VisitCafeListRepository

class VisitCafeListUseCase constructor(
    private val visitCafeListRepository: VisitCafeListRepository
) {
    suspend fun getVisitCafeList(): List<VisitedCafe> = visitCafeListRepository.getVisitCageList()
}