package com.eins.domain.usecase.cafe

import com.eins.domain.entity.cafe.find.FrequentlyCafeData
import com.eins.domain.repository.ICafeRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFrequentlyCafeUseCase @Inject constructor(
    private val iCafeRepository: ICafeRepository
) {
    suspend operator fun invoke() = flow<List<FrequentlyCafeData>> {
        emit(iCafeRepository.getFrequentlyCafeList())
    }
}