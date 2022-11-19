package com.example.data.repository

import com.example.data.service.ProgramService
import com.example.domain.model.ProgramModel
import com.example.domain.repository.ProgramRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class ProgramRepositoryImpl @Inject constructor(
    private val programService: ProgramService
) : ProgramRepository {

    override suspend fun getPrograms(): List<ProgramModel> {
        return withContext(Dispatchers.IO) {
            programService.getProgramItems()
        }
    }
}