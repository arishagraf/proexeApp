package com.example.domain.usecase

import com.example.domain.repository.ProgramRepository
import com.example.domain.utils.BaseUseCase
import com.example.domain.utils.None
import javax.inject.Inject

interface GetTabsUseCase {

    suspend fun execute(params: None): List<String>
}

class GetTabsUseCaseImpl @Inject constructor(
    private val programRepository: ProgramRepository
) : BaseUseCase<List<String>, None>(), GetTabsUseCase {

    override suspend fun execute(params: None): List<String> {
        return programRepository.getTabs()
    }
}