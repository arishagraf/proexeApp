package com.example.domain.usecase

import com.example.domain.model.ProgramModel
import com.example.domain.repository.ProgramRepository
import com.example.domain.utils.BaseUseCase
import com.example.domain.utils.None
import javax.inject.Inject

interface ShowProgramUseCase {

    suspend fun execute(params: None): List<ProgramModel>
}

class ShowProgramUseCaseImpl @Inject constructor(
    private val programRepository: ProgramRepository
) : BaseUseCase<List<ProgramModel>, None>(), ShowProgramUseCase {

    override suspend fun execute(params: None): List<ProgramModel> {
        return programRepository.showPrograms()
    }
}