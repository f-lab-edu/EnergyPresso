package com.eins.domain.usecase.log

import com.eins.domain.entity.ChargeUseLog
import com.eins.domain.repository.IUserRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUsePointLogUseCase @Inject constructor(
    private val iUserRepository: IUserRepository
) {
    suspend operator fun invoke() = flow<List<ChargeUseLog.UseLog>> {
        emit(iUserRepository.getUseLogList())
    }

}