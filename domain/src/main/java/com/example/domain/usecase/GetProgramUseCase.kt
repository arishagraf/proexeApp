package com.example.domain.usecase

import com.example.domain.utils.BaseUseCase

interface GetProgramUseCase {

    suspend fun execute(params: Unit)
}

class GetProgramUseCaseImpl: BaseUseCase<Unit, Unit>(), GetProgramUseCase {

    override suspend fun execute(params: Unit) {

    }
}