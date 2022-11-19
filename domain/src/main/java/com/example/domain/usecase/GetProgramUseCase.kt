package com.example.domain.usecase

import com.example.domain.repository.ProgramRepository
import com.example.domain.utils.BaseUseCase
import com.example.domain.utils.None
import javax.inject.Inject

interface GetProgramUseCase {

    suspend fun execute(params: None)
}

class GetProgramUseCaseImpl @Inject constructor(
    private val programRepository: ProgramRepository
) : BaseUseCase<Unit, None>(), GetProgramUseCase {

    override suspend fun execute(params: None) {
        programRepository.getPrograms()
    }
}