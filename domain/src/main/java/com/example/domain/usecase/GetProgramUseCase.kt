package com.example.domain.usecase

import com.example.domain.model.ProgramModel
import com.example.domain.repository.ProgramRepository
import com.example.domain.utils.BaseUseCase
import com.example.domain.utils.None
import javax.inject.Inject

interface GetProgramUseCase {

    suspend fun execute(params: None): List<ProgramModel>
}

class GetProgramUseCaseImpl @Inject constructor(
    private val programRepository: ProgramRepository
) : BaseUseCase<List<ProgramModel>, None>(), GetProgramUseCase {

    override suspend fun execute(params: None): List<ProgramModel> {
        return programRepository.getPrograms()
    }
}